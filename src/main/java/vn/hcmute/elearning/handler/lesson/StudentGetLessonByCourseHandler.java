package vn.hcmute.elearning.handler.lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.lesson.request.StudentGetLessonByCourseRequest;
import vn.hcmute.elearning.dtos.lesson.response.GetLessonByCourseResponse;
import vn.hcmute.elearning.dtos.lesson.response.LessonResponse;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.entity.Lesson;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ApproveStatus;
import vn.hcmute.elearning.enums.CourseStatus;
import vn.hcmute.elearning.enums.DisplayStatus;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.ICourseService;
import vn.hcmute.elearning.service.interfaces.ILessonService;
import vn.hcmute.elearning.service.interfaces.IUserService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentGetLessonByCourseHandler extends RequestHandler<StudentGetLessonByCourseRequest, GetLessonByCourseResponse> {
    private final ILessonService lessonService;
    private final ICourseService courseService;
    private final IUserService userService;
    @Override
    public GetLessonByCourseResponse handle(StudentGetLessonByCourseRequest request) {
        User user = userService.getUserById(request.getUserId());
        if (user == null || Boolean.TRUE.equals(user.getBan())) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        Course course = courseService.getCourseById(request.getCourseId());
        if (course == null || !ApproveStatus.APPROVE.equals(course.getApproveStatus()) || !CourseStatus.ACTIVE.equals(course.getStatus())) {
            throw new InternalException(ResponseCode.COURSE_NOT_FOUND);
        }
        List<Lesson> lessons = lessonService.getLessonByCourseIdAndStatus(course.getId(), DisplayStatus.VISIBLE);
        List<LessonResponse> responses = lessons.stream().map(LessonResponse::new).collect(Collectors.toList());
        return new GetLessonByCourseResponse(responses);
    }
}
