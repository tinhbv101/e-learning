package vn.hcmute.elearning.handler.exam_result;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.exam_result.request.GetDetailExamResultRequest;
import vn.hcmute.elearning.dtos.exam_result.response.GetDetailExamResultResponse;
import vn.hcmute.elearning.entity.ExamResult;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.IExamResultService;

@Component
@RequiredArgsConstructor
public class GetDetailExamResultHandler extends RequestHandler<GetDetailExamResultRequest, GetDetailExamResultResponse> {

    private final IExamResultService examResultService;

    @Override
    public GetDetailExamResultResponse handle(GetDetailExamResultRequest request) {
        ExamResult examResult = examResultService.getExamResultById(request.getId());
        if (examResult == null) {
            throw new InternalException(ResponseCode.EXAM_RESULT_NOT_FOUND);
        }
        return new GetDetailExamResultResponse(examResult);
    }
}
