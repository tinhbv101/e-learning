package vn.hcmute.elearning.dtos.exam_result.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.hcmute.elearning.core.BaseRequestData;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class GetDetailExamResultRequest extends BaseRequestData {
    private String id;
}
