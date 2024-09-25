package vn.hcmute.elearning.dtos.answer.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.dtos.question.response.QuestionResponse;
import vn.hcmute.elearning.utils.Paginate;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetSubmitAnswerByExamResultResponse extends BaseResponseData {

    private List<SubmitAnswer> answers;
    private Paginate paginate;

    public GetSubmitAnswerByExamResultResponse(List<SubmitAnswer> answers, Paginate paginate) {
        this.answers = answers;
        this.paginate = paginate;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SubmitAnswer {
        private QuestionResponse question;
        private AnswerResponse answer;
    }
}
