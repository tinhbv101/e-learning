package vn.hcmute.elearning.dtos.course.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.hcmute.elearning.core.BaseRequestData;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
public class BlockCourseRequest extends BaseRequestData {
    @NotBlank
    private String id;
}
