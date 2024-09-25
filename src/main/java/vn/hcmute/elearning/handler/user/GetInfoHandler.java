package vn.hcmute.elearning.handler.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.user.request.GetInfoRequest;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.IUserMapper;
import vn.hcmute.elearning.model.UserResponse;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Component
@RequiredArgsConstructor
public class GetInfoHandler extends RequestHandler<GetInfoRequest, UserResponse> {
    private final IUserService userService;
    private final IUserMapper userMapper;
    @Override
    public UserResponse handle(GetInfoRequest request) {
        User user = userService.getUserById(request.getUserId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        return new UserResponse(user);
    }
}
