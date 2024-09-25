package vn.hcmute.elearning.dtos.exam_result.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vn.hcmute.elearning.core.BaseRequestData;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class GetExamResultByExamIdRequest extends BaseRequestData {
    private String examId;
}
