package vn.hcmute.elearning.handler.lesson;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.lesson.request.UpdateLessonRequest;
import vn.hcmute.elearning.dtos.lesson.response.LessonResponse;
import vn.hcmute.elearning.entity.Lesson;
import vn.hcmute.elearning.service.interfaces.ILessonService;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateLessonHandler extends RequestHandler<UpdateLessonRequest, LessonResponse> {
    private final ILessonService lessonService;
    @Override
    public LessonResponse handle(UpdateLessonRequest request) {
        Lesson lesson = lessonService.getById(request.getId());
        if (StringUtils.isNotBlank(request.getName())) {
            lesson.setName(request.getName());
        }
        if (StringUtils.isNotBlank(request.getDescription())) {
            lesson.setDescription(request.getDescription());
        }
        lesson = lessonService.save(lesson);
        return new LessonResponse(lesson);
    }
}
