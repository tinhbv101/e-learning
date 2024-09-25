package vn.hcmute.elearning.dtos.course.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.hcmute.elearning.core.BaseRequestData;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetListCoursesRequest extends BaseRequestData {
    private String courseName;
    private LocalDate fromDate;
    private LocalDate toDate;
}
