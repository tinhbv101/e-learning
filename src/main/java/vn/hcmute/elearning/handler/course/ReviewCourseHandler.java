package vn.hcmute.elearning.handler.course;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.course.request.ReviewCourseRequest;
import vn.hcmute.elearning.dtos.course.response.ReviewCourseResponse;
import vn.hcmute.elearning.dtos.lesson.response.LessonResponse;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.entity.Lesson;
import vn.hcmute.elearning.enums.ApproveStatus;
import vn.hcmute.elearning.enums.CourseStatus;
import vn.hcmute.elearning.enums.DisplayStatus;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.ICourseService;
import vn.hcmute.elearning.service.interfaces.IExamService;
import vn.hcmute.elearning.service.interfaces.ILessonService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReviewCourseHandler extends RequestHandler<ReviewCourseRequest, ReviewCourseResponse> {

    private final ICourseService courseService;
    private final ILessonService lessonService;
    private final IExamService examService;

    @Override
    public ReviewCourseResponse handle(ReviewCourseRequest request) {
        Course course = courseService.getCourseById(request.getId());
        if (course == null || CourseStatus.INACTIVE.equals(course.getStatus()) || !ApproveStatus.APPROVE.equals(course.getApproveStatus())) {
            throw new InternalException(ResponseCode.COURSE_INVALID);
        }
        List<Lesson> lessons = lessonService.getLessonByCourseIdAndStatus(course.getId(), DisplayStatus.VISIBLE);
        List<LessonResponse> lessonResponses = lessons.stream().map(LessonResponse::new).collect(Collectors.toList());
        ReviewCourseResponse response = new ReviewCourseResponse(course);
        response.setLessons(lessonResponses);
        response.setExamsCount(examService.countByCourse(course.getId()));
        return response;
    }
}
