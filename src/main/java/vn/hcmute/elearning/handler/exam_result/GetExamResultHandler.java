package vn.hcmute.elearning.handler.exam_result;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.exam_result.request.GetExamResultRequest;
import vn.hcmute.elearning.dtos.exam_result.response.GetDetailExamResultResponse;
import vn.hcmute.elearning.entity.Exam;
import vn.hcmute.elearning.entity.ExamResult;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.IExamResultService;
import vn.hcmute.elearning.service.interfaces.IExamService;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Component
@RequiredArgsConstructor
public class GetExamResultHandler extends RequestHandler<GetExamResultRequest, GetDetailExamResultResponse> {

    private final IExamResultService examResultService;
    private final IUserService userService;
    private final IExamService examService;
    @Override
    public GetDetailExamResultResponse handle(GetExamResultRequest request) {
        User user = userService.getUserById(request.getUserId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        Exam exam = examService.getExamById(request.getExamId());
        if (exam == null) {
            throw new InternalException(ResponseCode.EXAM_NOT_FOUND);
        }
        ExamResult examResult = examResultService.getByStudentAndExam(user.getId(), exam.getId());
        if (examResult == null) {
            throw new InternalException(ResponseCode.EXAM_RESULT_NOT_FOUND);
        }
        return new GetDetailExamResultResponse(examResult);
    }
}
