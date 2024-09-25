package vn.hcmute.elearning.handler.user;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.user.request.CmsDeleteUserV2Request;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.KeycloakService;
import vn.hcmute.elearning.service.interfaces.IUserService;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class CmsDeleteUserV2Handler extends RequestHandler<CmsDeleteUserV2Request, StatusResponse> {

    private final IUserService userService;
    private final KeycloakService keycloakService;

    @Override
    public StatusResponse handle(CmsDeleteUserV2Request request) {
        User user = userService.getUserById(request.getId());
        if (Objects.isNull(user)) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        if (user.getDelete()) {
            throw new InternalException(ResponseCode.USER_WAS_DELETED);
        }
        keycloakService.deleteUser(user.getId());
        user.setDelete(true);
        userService.updateUser(user);
        return new StatusResponse(true);
    }
}
