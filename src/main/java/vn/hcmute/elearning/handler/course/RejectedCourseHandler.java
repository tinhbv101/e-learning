package vn.hcmute.elearning.handler.course;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.course.request.RejectedCourseRequest;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.enums.ApproveStatus;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.ICourseService;

@Component
@RequiredArgsConstructor
@Slf4j
public class RejectedCourseHandler extends RequestHandler<RejectedCourseRequest, StatusResponse> {
    private final ICourseService iCourseService;

    @Override
    public StatusResponse handle(RejectedCourseRequest request) {
        Course course = iCourseService.getCourseById(request.getId());
        if (course == null) {
            throw new InternalException(ResponseCode.COURSE_NOT_FOUND);
        }
        course.setApproveStatus(ApproveStatus.REJECTED);

        iCourseService.save(course);
        return new StatusResponse();
    }
}
