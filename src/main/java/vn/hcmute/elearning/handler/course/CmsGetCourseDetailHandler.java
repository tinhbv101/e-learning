package vn.hcmute.elearning.handler.course;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.course.request.CmsGetCourseDetailRequest;
import vn.hcmute.elearning.dtos.course.response.CmsGetDetailCourseResponse;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.ICourseService;

@Component
@Slf4j
@RequiredArgsConstructor
public class CmsGetCourseDetailHandler extends RequestHandler<CmsGetCourseDetailRequest, CmsGetDetailCourseResponse> {
    private final ICourseService iCourseService;

    @Override
    public CmsGetDetailCourseResponse handle(CmsGetCourseDetailRequest request) {
        Course course = iCourseService.getCourseById(request.getId());
        if (course == null) {
            throw new InternalException(ResponseCode.COURSE_NOT_FOUND);
        }
        return iCourseService.getDetailByTeacher(request.getId());
    }
}
