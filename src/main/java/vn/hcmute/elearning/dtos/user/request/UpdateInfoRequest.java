package vn.hcmute.elearning.dtos.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vn.hcmute.elearning.core.BaseRequestData;
import vn.hcmute.elearning.enums.Gender;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInfoRequest extends BaseRequestData {
    private String avatar;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private Gender gender;
    private int homeNumber;
    private String streetName;
    private Long provinceId;
    private Long districtId;
    private Long wardId;
}
