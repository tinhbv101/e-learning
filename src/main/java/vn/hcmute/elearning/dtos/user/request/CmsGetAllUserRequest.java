package vn.hcmute.elearning.dtos.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.hcmute.elearning.core.BaseRequestData;
import vn.hcmute.elearning.enums.Gender;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CmsGetAllUserRequest extends BaseRequestData {
    private String firstName;
    private String lastName;
    private Gender gender;
    private Boolean active;
    private Boolean delete;
    private Boolean ban;
    private Boolean isOcr;
    private LocalDate createFrom;
    private LocalDate createTo;
    private Boolean isTeacher;


}
