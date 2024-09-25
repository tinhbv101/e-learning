package vn.hcmute.elearning.dtos.course.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.enums.ApproveStatus;
import vn.hcmute.elearning.enums.CourseStatus;
import vn.hcmute.elearning.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CmsGetDetailCourseResponse extends BaseResponseData {
    private String id;
    private String courseName;
    private String description;
    private Long price;
    private String imageUrl;
    private CourseStatus status;
    private ApproveStatus approveStatus;
    private LocalDateTime createDate;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private Gender gender;
    private String email;
    private String phone;
    private String avatar;
}
