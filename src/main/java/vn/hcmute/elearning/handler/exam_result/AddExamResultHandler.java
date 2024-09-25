package vn.hcmute.elearning.handler.exam_result;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.exam_result.request.AddExamResultRequest;
import vn.hcmute.elearning.dtos.exam_result.response.AddExamResultResponse;
import vn.hcmute.elearning.entity.Exam;
import vn.hcmute.elearning.entity.ExamResult;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ExamResultStatus;
import vn.hcmute.elearning.enums.ExamStatus;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.IExamResultService;
import vn.hcmute.elearning.service.interfaces.IExamService;
import vn.hcmute.elearning.service.interfaces.IUserService;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AddExamResultHandler extends RequestHandler<AddExamResultRequest, AddExamResultResponse> {

    private final IExamResultService examResultService;
    private final IExamService examService;
    private final IUserService userService;

    @Override
    public AddExamResultResponse handle(AddExamResultRequest request) {
        User user = userService.getUserById(request.getUserId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        Exam exam = examService.getExamById(request.getExamId());
        if (exam == null || ExamStatus.INACTIVE.equals(exam.getStatus())) {
            throw new InternalException(ResponseCode.EXAM_NOT_FOUND);
        }
        Double maxExamScore = examService.getMaxExamScore(exam.getId());
        if (request.getScore() > maxExamScore) {
            throw new InternalException(ResponseCode.SCORE_INVALID);
        }
        ExamResult examResult = new ExamResult()
            .setComment(request.getComment())
            .setExam(exam)
            .setUser(user)
            .setScore(request.getScore())
            .setCorrectTotal(request.getCorrectTotal())
            .setStatus(ExamResultStatus.COMPLETE)
            .setTime(LocalDateTime.now());
        examResult = examResultService.save(examResult);
        if (examResult == null) {
            throw new InternalException(ResponseCode.EXAM_RESULT_CREATED_FAILED);
        }
        return new AddExamResultResponse(examResult);
    }
}
