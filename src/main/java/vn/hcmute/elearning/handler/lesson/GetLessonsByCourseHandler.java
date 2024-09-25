package vn.hcmute.elearning.handler.lesson;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.lesson.request.GetLessonByCourseRequest;
import vn.hcmute.elearning.dtos.lesson.response.GetLessonByCourseResponse;
import vn.hcmute.elearning.dtos.lesson.response.LessonResponse;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.entity.Lesson;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.ICourseService;
import vn.hcmute.elearning.service.interfaces.ILessonService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetLessonsByCourseHandler extends RequestHandler<GetLessonByCourseRequest, GetLessonByCourseResponse> {

    private final ILessonService lessonService;
    private final ICourseService courseService;

    @Override
    public GetLessonByCourseResponse handle(GetLessonByCourseRequest request) {
        Course course = courseService.getByTeacherOrUser(request.getCourseId(), request.getUserId());
        if (course == null) {
            throw new InternalException(ResponseCode.COURSE_NOT_FOUND);
        }
        List<Lesson> lessonPage = lessonService.getLessonsByCourse(request.getCourseId());
        List<LessonResponse> lessonResponseList = lessonPage.stream().map(LessonResponse::new).collect(Collectors.toList());
        return new GetLessonByCourseResponse(lessonResponseList);
    }
}

