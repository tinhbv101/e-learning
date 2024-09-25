package vn.hcmute.elearning.dtos.answer.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import vn.hcmute.elearning.core.BaseRequestData;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubmitAnswersRequest extends BaseRequestData {
    private String code;
    private List<@Valid SubmitAnswerRequest> answers;
}
