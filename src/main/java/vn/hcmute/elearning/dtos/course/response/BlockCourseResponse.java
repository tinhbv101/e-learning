package vn.hcmute.elearning.dtos.course.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.model.course.CourseDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlockCourseResponse extends BaseResponseData {
    private CourseDto course;
}
