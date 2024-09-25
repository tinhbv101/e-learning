package vn.hcmute.elearning.dtos.course.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.model.course.CourseDto;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class UpdateCourseResponse extends BaseResponseData {
    private CourseDto course;
}
