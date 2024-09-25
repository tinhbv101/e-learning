package vn.hcmute.elearning.handler.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.user.request.CmsGetInfoByIdRequest;
import vn.hcmute.elearning.dtos.user.response.GetInfoResponse;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.IUserMapper;
import vn.hcmute.elearning.model.user.UserDto;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Component
@RequiredArgsConstructor
public class CmsGetUserByIdHandler extends RequestHandler<CmsGetInfoByIdRequest, GetInfoResponse> {
    private final IUserService userService;
    private final IUserMapper userMapper;

    @Override
    public GetInfoResponse handle(CmsGetInfoByIdRequest request) {
        User user = userService.getUserById(request.getId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        UserDto userDto = userMapper.toUserDto(user);
        return new GetInfoResponse(userDto);
    }
}
