package vn.hcmute.elearning.dtos.exam_result.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseRequestData;
import vn.hcmute.elearning.enums.ExamResultStatus;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUngradedExamsRequest extends BaseRequestData {
    @NotBlank
    private String examId;
    private ExamResultStatus status;
}
