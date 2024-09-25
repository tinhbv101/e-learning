package vn.hcmute.elearning.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.enums.Gender;
import vn.hcmute.elearning.model.address.DistrictDto;
import vn.hcmute.elearning.model.address.ProvinceDto;
import vn.hcmute.elearning.model.address.WardDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;

    private String firstName;

    private String lastName;

    private LocalDate birthday;

    private Gender gender;

    private String email;

    private String phone;

    private ProvinceDto province;

    private DistrictDto district;

    private WardDto ward;

    private String streetName;

    private int homeNumber;

    private Boolean isOrc;

    private boolean active;

    private String avatar;

    private Boolean ban;

    private Boolean isOnline;

    private LocalDateTime createDate;
}
