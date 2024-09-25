package vn.hcmute.elearning.handler.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.user.request.CmsDeleteUserRequest;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.KeycloakService;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Component
@RequiredArgsConstructor
public class CmsDeleteUserHandler extends RequestHandler<CmsDeleteUserRequest, StatusResponse> {
    private final IUserService userService;
    private final KeycloakService keycloakService;

    @Override
    public StatusResponse handle(CmsDeleteUserRequest request) {
        User user = userService.getUserById(request.getUserId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        boolean success = keycloakService.deleteUser(user.getId());
        if (!success) {
            return new StatusResponse(false);
        }
        userService.deleteUser(user.getId());
        return new StatusResponse(true);
    }
}
