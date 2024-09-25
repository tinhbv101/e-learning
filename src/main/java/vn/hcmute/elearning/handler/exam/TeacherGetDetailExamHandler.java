package vn.hcmute.elearning.handler.exam;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.exam.request.TeacherGetDetailExamRequest;
import vn.hcmute.elearning.dtos.exam.response.ExamResponse;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.entity.Exam;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.ICourseService;
import vn.hcmute.elearning.service.interfaces.IExamResultService;
import vn.hcmute.elearning.service.interfaces.IExamService;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Component
@RequiredArgsConstructor
public class TeacherGetDetailExamHandler extends RequestHandler<TeacherGetDetailExamRequest, ExamResponse> {

    private final IExamService examService;
    private final IUserService userService;
    private final IExamResultService examResultService;
    private final ICourseService courseService;


    @Override
    public ExamResponse handle(TeacherGetDetailExamRequest request) {
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
        int testAttempts = examResultService.getTestAttempts(user.getId(), exam.getId(), true);
        Double maxExamScore = examService.getMaxExamScore(exam.getId());
        return new ExamResponse(exam).setTotalAttemptsCompleted(testAttempts).setExamMaxScore(maxExamScore);
    }
}
