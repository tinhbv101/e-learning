package vn.hcmute.elearning.handler.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.user.request.UpdateInfoRequest;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.model.UserResponse;
import vn.hcmute.elearning.service.AddressService;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Component
@RequiredArgsConstructor
public class UpdateInfoHandler extends RequestHandler<UpdateInfoRequest, UserResponse> {
    private final IUserService userService;
    private final AddressService addressService;

    @Override
    public UserResponse handle(UpdateInfoRequest request) {
        User user = userService.getUserById(request.getUserId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setBirthday(request.getBirthday());
        user.setAvatar(request.getAvatar());
        user.setGender(request.getGender());
        if (request.getProvinceId() != null) {
            user.setProvince(addressService.findProvinceById(request.getProvinceId()));
        }
        if (request.getDistrictId() != null) {
            user.setDistrict(addressService.findDistrictById(request.getDistrictId()));
        }
        if (request.getWardId() != null) {
            user.setWard(addressService.findWardById(request.getWardId()));
        }
        user.setHomeNumber(request.getHomeNumber());
        user.setStreetName(request.getStreetName());

        user = userService.updateUser(user);
        return new UserResponse(user);
    }
}
