package vn.hcmute.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.hcmute.elearning.controller.interfaces.ILessonController;
import vn.hcmute.elearning.core.BaseController;
import vn.hcmute.elearning.core.ResponseBase;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.lesson.request.*;
import vn.hcmute.elearning.dtos.lesson.response.GetLessonByCourseResponse;
import vn.hcmute.elearning.dtos.lesson.response.LessonResponse;

@RestController
public class LessonController extends BaseController implements ILessonController {

    @Override
    public ResponseEntity<ResponseBase<GetLessonByCourseResponse>> getLessonsByCourse(String courseId) {
        return this.execute(new GetLessonByCourseRequest(courseId));
    }

    @Override
    public ResponseEntity<ResponseBase<LessonResponse>> createLesson(CreateLessonRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<LessonResponse>> updateLesson(UpdateLessonRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> deleteLesson(String id) {
        return this.execute(new DeleteLessonRequest(id));
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> displayLesson(DisplayLessonRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<GetLessonByCourseResponse>> studentGetLessonsByCourse(String courseId) {
        return this.execute(new StudentGetLessonByCourseRequest(courseId));
    }
}
