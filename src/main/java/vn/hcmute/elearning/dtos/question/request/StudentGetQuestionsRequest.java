package vn.hcmute.elearning.dtos.question.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseRequestData;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentGetQuestionsRequest extends BaseRequestData {
    private String id;
    @NotBlank
    private String examId;
}
