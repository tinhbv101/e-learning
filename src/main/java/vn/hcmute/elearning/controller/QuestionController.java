package vn.hcmute.elearning.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.hcmute.elearning.controller.interfaces.IQuestionController;
import vn.hcmute.elearning.core.BaseController;
import vn.hcmute.elearning.core.ResponseBase;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.question.request.*;
import vn.hcmute.elearning.dtos.question.response.CreateQuestionFromExcelResponse;
import vn.hcmute.elearning.dtos.question.response.DeleteQuestionResponse;
import vn.hcmute.elearning.dtos.question.response.GetQuestionResponse;
import vn.hcmute.elearning.dtos.question.response.QuestionResponse;

@RestController
public class QuestionController extends BaseController implements IQuestionController {
    @Override
    public ResponseEntity<ResponseBase<QuestionResponse>> createQuestion(CreateQuestionRequest request) {
        return this.execute(request, QuestionResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> createListQuestion(CreateListQuestionRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<CreateQuestionFromExcelResponse>> createQuestionUsingExcel(CreateQuestionFromExcelRequest request) {
        return this.execute(request, CreateQuestionFromExcelResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<QuestionResponse>> updateQuestion(UpdateQuestionRequest request) {
        return this.execute(request, QuestionResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<DeleteQuestionResponse>> deleteQuestionById(String id) {
        DeleteQuestionByIdRequest request = new DeleteQuestionByIdRequest(id);
        return this.execute(request, DeleteQuestionResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<DeleteQuestionResponse>> deleteQuestionByExamId(String examId) {
        DeleteQuestionsByExamIdRequest request = new DeleteQuestionsByExamIdRequest(examId);
        return this.execute(request, DeleteQuestionResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<GetQuestionResponse>> getQuestionByExamId(GetQuestionRequest request, Pageable pageable) {
        request.setPageable(pageable);
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<GetQuestionResponse>> studentGetQuestionByExamId(StudentGetQuestionsRequest request, Pageable pageable) {
        request.setPageable(pageable);
        return this.execute(request);
    }
}
