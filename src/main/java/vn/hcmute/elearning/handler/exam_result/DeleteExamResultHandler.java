package vn.hcmute.elearning.handler.exam_result;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.exam_result.request.DeleteExamResultRequest;
import vn.hcmute.elearning.entity.ExamResult;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.IExamResultService;

@Component
@RequiredArgsConstructor
public class DeleteExamResultHandler extends RequestHandler<DeleteExamResultRequest, StatusResponse> {

    private final IExamResultService examResultService;

    @Override
    public StatusResponse handle(DeleteExamResultRequest request) {
        ExamResult examResult = examResultService.getExamResultById(request.getId());
        if (examResult == null) {
            throw new InternalException(ResponseCode.EXAM_RESULT_NOT_FOUND);
        }
        examResultService.deleteExamResultById(request.getId());
        return new StatusResponse(true);
    }
}
