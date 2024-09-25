package vn.hcmute.elearning.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.hcmute.elearning.controller.interfaces.IExamResultController;
import vn.hcmute.elearning.core.BaseController;
import vn.hcmute.elearning.core.ResponseBase;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.exam_result.request.*;
import vn.hcmute.elearning.dtos.exam_result.response.*;

@RestController
public class ExamResultController extends BaseController implements IExamResultController {
    @Override
    public ResponseEntity<ResponseBase<GetDetailExamResultResponse>> getDetailExamResult(String id) {
        GetDetailExamResultRequest request = new GetDetailExamResultRequest(id);
        return this.execute(request, GetDetailExamResultResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<AddExamResultResponse>> createExam(AddExamResultRequest request) {
        return this.execute(request, AddExamResultResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<UpdateExamResultResponse>> updateExam(UpdateExamResultRequest request) {
        return this.execute(request, UpdateExamResultResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> deleteExam(String id) {
        DeleteExamResultRequest request = new DeleteExamResultRequest(id);
        return this.execute(request, StatusResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<GetExamResultByExamIdResponse>> getByExamId(String examId, Pageable pageable) {
        GetExamResultByExamIdRequest request = new GetExamResultByExamIdRequest(examId);
        request.setPageable(pageable);
        return this.execute(request, GetExamResultByExamIdResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<GetDetailExamResultResponse>> getExamResult(String examId) {
        GetExamResultRequest request = new GetExamResultRequest(examId);
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<GetUngradedExamsResponse>> getUngradedExams(GetUngradedExamsRequest request, Pageable pageable) {
        request.setPageable(pageable);
        return this.execute(request, GetUngradedExamsResponse.class);
    }
}
