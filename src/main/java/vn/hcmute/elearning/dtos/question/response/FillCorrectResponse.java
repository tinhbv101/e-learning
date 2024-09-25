package vn.hcmute.elearning.dtos.question.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.entity.FillCorrect;
import vn.hcmute.elearning.enums.FillType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FillCorrectResponse {
    private String id;
    private Integer index;
    private String answer;
    private FillType type;

    public FillCorrectResponse(FillCorrect fillCorrect) {
        this.id = fillCorrect.getId();
        this.index = fillCorrect.getIndex();
        this.answer = fillCorrect.getAnswer();
        this.type = fillCorrect.getType();

    }
    public FillCorrectResponse(FillCorrect fillCorrect, boolean isStudent) {
        this.id = fillCorrect.getId();
        this.index = fillCorrect.getIndex();
        this.type = fillCorrect.getType();
        if (!isStudent) {
            this.answer = fillCorrect.getAnswer();
        }
    }
}
