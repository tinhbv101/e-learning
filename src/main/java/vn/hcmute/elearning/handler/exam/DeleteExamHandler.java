package vn.hcmute.elearning.handler.exam;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.exam.request.DeleteExamRequest;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.entity.Exam;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.ICourseService;
import vn.hcmute.elearning.service.interfaces.IExamService;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Component
@RequiredArgsConstructor
public class DeleteExamHandler extends RequestHandler<DeleteExamRequest, StatusResponse> {

    private final IExamService examService;
    private final ICourseService courseService;
    private final IUserService userService;

    @Override
    public StatusResponse handle(DeleteExamRequest request) {
        User user = userService.getUserById(request.getUserId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        Course course = courseService.getCourseByExamId(request.getId());
        if (!user.getId().equals(course.getCreatedBy())) {
            throw new InternalException(ResponseCode.COURSE_INVALID);
        }
        Exam exam = examService.getExamById(request.getId());
        if (exam == null) {
            throw new InternalException(ResponseCode.EXAM_NOT_FOUND);
        }
        examService.deleteExamById(request.getId());
        return new StatusResponse(true);
    }
}
