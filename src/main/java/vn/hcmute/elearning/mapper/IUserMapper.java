package vn.hcmute.elearning.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import vn.hcmute.elearning.entity.District;
import vn.hcmute.elearning.entity.Province;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.entity.Ward;
import vn.hcmute.elearning.model.address.DistrictDto;
import vn.hcmute.elearning.model.address.ProvinceDto;
import vn.hcmute.elearning.model.address.WardDto;
import vn.hcmute.elearning.model.chat.UserInfoChat;
import vn.hcmute.elearning.model.user.UserDto;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface IUserMapper {

    UserDto toUserDto(User user);

    User toUser(UserDto userDto);

    ProvinceDto toProvinceDto(Province province);

    Province toProvince(ProvinceDto provinceDto);

    WardDto toWardDto(Ward ward);

    Ward toWard(WardDto wardDto);

    DistrictDto toDistrictDto(District district);

    District toDistrict(DistrictDto districtDto);

    List<UserDto> toListUserDto(List<User> users);
    List<UserInfoChat> toListUserInfoChat(List<User> users);
}
