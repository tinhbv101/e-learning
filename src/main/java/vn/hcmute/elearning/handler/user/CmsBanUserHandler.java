package vn.hcmute.elearning.handler.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.user.request.CmsBanUserRequest;
import vn.hcmute.elearning.dtos.user.response.CmsBanUserResponse;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.KeycloakService;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Component
@RequiredArgsConstructor
public class CmsBanUserHandler extends RequestHandler<CmsBanUserRequest, CmsBanUserResponse> {
    private final IUserService userService;
    private final KeycloakService keycloakService;

    @Override
    public CmsBanUserResponse handle(CmsBanUserRequest request) {
        User user = userService.getUserById(request.getId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        user.setBan(true);
        userService.updateUser(user);
        keycloakService.revokeAllSessionUser(user.getId());
        return new CmsBanUserResponse("Ban thành công", true);
    }
}
