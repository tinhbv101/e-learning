package vn.hcmute.elearning.handler.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.question.request.DeleteQuestionsByExamIdRequest;
import vn.hcmute.elearning.dtos.question.response.DeleteQuestionResponse;
import vn.hcmute.elearning.entity.Exam;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.IExamResultService;
import vn.hcmute.elearning.service.interfaces.IExamService;
import vn.hcmute.elearning.service.interfaces.IQuestionService;

@Component
@RequiredArgsConstructor
public class DeleteQuestionByExamIdHandler extends RequestHandler<DeleteQuestionsByExamIdRequest, DeleteQuestionResponse> {
    private final IQuestionService questionService;
    private final IExamService examService;
    private final IExamResultService examResultService;

    @Override
    public DeleteQuestionResponse handle(DeleteQuestionsByExamIdRequest request) {
        Exam exam = examService.getExamById(request.getExamId());
        if (exam == null) {
            throw new InternalException(ResponseCode.EXAM_NOT_FOUND);
        }
        if (examResultService.getTestAttempts(null, exam.getId(), true) > 0) {
            throw new InternalException(ResponseCode.HAVE_ATTEMPTS);
        }
        questionService.deleteQuestionByExam(exam);
        return new DeleteQuestionResponse(true);
    }
}
