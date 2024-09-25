package vn.hcmute.elearning.handler.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.common.Constant;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.answer.request.GetCurrentAnswerRequest;
import vn.hcmute.elearning.dtos.answer.request.SubmitAnswersRequest;
import vn.hcmute.elearning.dtos.answer.response.GetCurrentAnswerResponse;
import vn.hcmute.elearning.entity.Exam;
import vn.hcmute.elearning.entity.ExamResult;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.RedisService;
import vn.hcmute.elearning.service.interfaces.IExamResultService;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class GetCurrentAnswersHandler extends RequestHandler<GetCurrentAnswerRequest, GetCurrentAnswerResponse> {

    private final IExamResultService examResultService;
    private final RedisService redisService;

    @Override
    public GetCurrentAnswerResponse handle(GetCurrentAnswerRequest request) {
        ExamResult examResult = examResultService.getByCodeNotNull(request.getCode());
        if (examResult == null) {
            throw new InternalException(ResponseCode.EXAM_NOT_FOUND);
        }
        if (examResult.getScore() != null) {
            throw new InternalException(ResponseCode.EXAM_RESULT_COMPLETED);
        }
        Exam exam = examResult.getExam();
        Long timeSecond = Timestamp.valueOf(examResult.getCreateDate().plusMinutes(exam.getTimeMinute())).getTime();
        SubmitAnswersRequest value = redisService.getValue(Constant.REDIS_ANSWERS_PREFIX + request.getCode(), SubmitAnswersRequest.class);
        if (value == null) {
            return new GetCurrentAnswerResponse().setTimestamp(timeSecond);
        }
        return new GetCurrentAnswerResponse(value.getAnswers(), timeSecond);
    }
}
