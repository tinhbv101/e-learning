package vn.hcmute.elearning.handler.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.user.request.CmsUpdateInfoByIdRequest;
import vn.hcmute.elearning.dtos.user.response.UpdateInfoResponse;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.AddressService;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Component
@RequiredArgsConstructor
public class CmsUpdateInfoByIdHandler extends RequestHandler<CmsUpdateInfoByIdRequest, UpdateInfoResponse> {
    private final IUserService userService;
    private final AddressService addressService;

    @Override
    public UpdateInfoResponse handle(CmsUpdateInfoByIdRequest request) {
        User user = userService.getUserById(request.getId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setBirthday(request.getBirthday());
        user.setGender(request.getGender());
        if (request.getAvatar() != null && !request.getAvatar().equals("")) {
            user.setAvatar(request.getAvatar());
        }
        user.setProvince(addressService.findProvinceById(request.getProvinceId()));
        user.setDistrict(addressService.findDistrictById(request.getDistrictId()));
        user.setWard(addressService.findWardById(request.getWardId()));
        user.setHomeNumber(request.getHomeNumber());
        user.setStreetName(request.getStreetName());

        user = userService.updateUser(user);
        return new UpdateInfoResponse(user);
    }
}
