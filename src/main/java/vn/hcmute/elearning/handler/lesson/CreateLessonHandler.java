package vn.hcmute.elearning.handler.lesson;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.lesson.request.CreateLessonRequest;
import vn.hcmute.elearning.dtos.lesson.response.LessonResponse;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.entity.Lesson;
import vn.hcmute.elearning.enums.DisplayStatus;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.ICourseService;
import vn.hcmute.elearning.service.interfaces.ILessonService;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateLessonHandler extends RequestHandler<CreateLessonRequest, LessonResponse> {
    private final ILessonService lessonService;
    private final ICourseService courseService;
    @Override
    public LessonResponse handle(CreateLessonRequest request) {
        Course course = courseService.getCourseById(request.getCourseId());
        if (course == null) {
            throw new InternalException(ResponseCode.COURSE_NOT_FOUND);
        }
        Lesson lesson = new Lesson()
            .setCourse(course)
            .setName(request.getName())
            .setDisplayStatus(DisplayStatus.HIDE)
            .setDescription(request.getDescription());
        lesson  = lessonService.save(lesson);
        return new LessonResponse(lesson);
    }
}
