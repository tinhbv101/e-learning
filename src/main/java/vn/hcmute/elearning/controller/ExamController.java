package vn.hcmute.elearning.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.hcmute.elearning.controller.interfaces.IExamController;
import vn.hcmute.elearning.core.BaseController;
import vn.hcmute.elearning.core.ResponseBase;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.exam.request.*;
import vn.hcmute.elearning.dtos.exam.response.ExamResponse;
import vn.hcmute.elearning.dtos.exam.response.GetExamByLessonResponse;

import java.security.Principal;

@RestController
public class ExamController extends BaseController implements IExamController {
    @Override
    public ResponseEntity<ResponseBase<ExamResponse>> getDetailExam(String id) {
        GetDetailExamRequest request = new GetDetailExamRequest();
        request.setId(id);
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<ExamResponse>> createExam(AddExamRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<ExamResponse>> updateExam(UpdateExamRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> deleteExam(String id) {
        DeleteExamRequest request = new DeleteExamRequest();
        request.setId(id);
        return this.execute(request, StatusResponse.class);
    }
    @Override
    public ResponseEntity<ResponseBase<GetExamByLessonResponse>> getExamByLesson(GetExamByLessonRequest request,
                                                                                 Pageable pageable, Principal principal) {
        request.setPageable(pageable);
        request.setUserId(principal.getName());
        return this.execute(request, GetExamByLessonResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<GetExamByLessonResponse>> studentGetExamByLesson(StudentGetExamByLessonRequest request, Pageable pageable, Principal principal) {
        request.setPageable(pageable);
        request.setUserId(principal.getName());
        return this.execute(request, GetExamByLessonResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<ExamResponse>> teacherGetDetailExam(String id) {
        TeacherGetDetailExamRequest request = new TeacherGetDetailExamRequest();
        request.setId(id);
        return this.execute(request);
    }
}
