package vn.hcmute.elearning.handler.lesson;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.lesson.request.DeleteLessonRequest;
import vn.hcmute.elearning.entity.Lesson;
import vn.hcmute.elearning.service.interfaces.ILessonService;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteLessonHandler extends RequestHandler<DeleteLessonRequest, StatusResponse> {
    private final ILessonService lessonService;
    @Override
    public StatusResponse handle(DeleteLessonRequest request) {
        Lesson lesson = lessonService.getById(request.getId());
        lessonService.deleteById(lesson.getId());
        return new StatusResponse(true);
    }
}
