package vn.hcmute.elearning.dtos.answer.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.entity.FillAnswer;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FillAnswerResponse {
    private String id;
    private String answer;

    public FillAnswerResponse(FillAnswer fillAnswer) {
        this.id = fillAnswer.getFillCorrect().getId();
        this.answer = fillAnswer.getAnswer();
    }
}
