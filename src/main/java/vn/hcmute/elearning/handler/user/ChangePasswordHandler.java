package vn.hcmute.elearning.handler.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.auth.response.AccessTokenResponseCustom;
import vn.hcmute.elearning.dtos.user.request.ChangePasswordRequest;
import vn.hcmute.elearning.dtos.user.response.ChangePasswordResponse;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.KeycloakService;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Component
@RequiredArgsConstructor
public class ChangePasswordHandler extends RequestHandler<ChangePasswordRequest, ChangePasswordResponse> {
    private final IUserService userService;
    private final KeycloakService keycloakService;

    @Override
    public ChangePasswordResponse handle(ChangePasswordRequest request) {
        User user = userService.getUserById(request.getUserId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        if (!request.getPasswordNew().equals(request.getPasswordConfirm())) {
            throw new InternalException(ResponseCode.PASSWORD_NOT_MATCH);
        }
        AccessTokenResponseCustom tokenResponseCustom = keycloakService.getUserJWT(user.getEmail(), request.getPasswordCurrent());
        if (tokenResponseCustom == null) {
            throw new InternalException(ResponseCode.PASSWORD_INCORRECT);
        }
        boolean isSuccess = keycloakService.setUserPassword(user.getId(), request.getPasswordNew());
        if (!isSuccess) {
            return new ChangePasswordResponse("Đổi mật khẩu thất bại", false);
        }
        return new ChangePasswordResponse("Đổi mật khẩu thành công", true);
    }
}
