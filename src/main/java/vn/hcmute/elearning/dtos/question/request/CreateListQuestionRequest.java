package vn.hcmute.elearning.dtos.question.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseRequestData;
import vn.hcmute.elearning.model.QuestionInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateListQuestionRequest extends BaseRequestData {
    @NotBlank
    private String examId;
    private List<@Valid QuestionInfo> questions;
}


