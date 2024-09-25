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
public class RegisterV2Request extends BaseRequestData {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private LocalDate birthday;
    private Gender gender;
}
