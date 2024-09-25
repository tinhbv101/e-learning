package vn.hcmute.elearning.handler.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.user.request.CmsUnbanUserRequest;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Component
@RequiredArgsConstructor
public class CmsUnbanUserHandler extends RequestHandler<CmsUnbanUserRequest, StatusResponse> {
    private final IUserService userService;

    @Override
    public StatusResponse handle(CmsUnbanUserRequest request) {
        User user = userService.getUserById(request.getId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        user.setBan(false);
        userService.updateUser(user);
        return new StatusResponse();
    }
}
