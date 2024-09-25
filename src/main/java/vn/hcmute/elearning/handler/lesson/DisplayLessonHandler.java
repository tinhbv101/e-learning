package vn.hcmute.elearning.handler.lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.lesson.request.DisplayLessonRequest;
import vn.hcmute.elearning.entity.Lesson;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.ILessonService;

@Component
@RequiredArgsConstructor
public class DisplayLessonHandler extends RequestHandler<DisplayLessonRequest, StatusResponse> {
    private final ILessonService iLessonService;

    @Override
    public StatusResponse handle(DisplayLessonRequest request) {
        Lesson lesson = iLessonService.getByIdAndCreateBy(request.getLessonId(), request.getUserId());
        if (lesson == null) {
            throw new InternalException(ResponseCode.LESSON_NOT_FOUND);
        }
        if (lesson.getDisplayStatus().equals(request.getStatus())) {
            throw new InternalException(ResponseCode.LESSON_DISPLAY_FAILED);
        }
        lesson.setDisplayStatus(request.getStatus());
        iLessonService.save(lesson);
        return new StatusResponse();
    }
}
