package vn.hcmute.elearning.handler.course;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.course.request.GetDetailCourseRequest;
import vn.hcmute.elearning.dtos.course.response.GetDetailCourseResponse;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.ICourseMapper;
import vn.hcmute.elearning.service.interfaces.ICourseService;

@Component
@RequiredArgsConstructor
public class GetDetailCourseHandler extends RequestHandler<GetDetailCourseRequest, GetDetailCourseResponse> {

    private final ICourseService courseService;
    private final ICourseMapper courseMapper;

    @Override
    public GetDetailCourseResponse handle(GetDetailCourseRequest request) {
        Course course = courseService.getCourseById(request.getId());
        if (course == null) {
            throw new InternalException(ResponseCode.COURSE_NOT_FOUND);
        }
        return new GetDetailCourseResponse(courseMapper.toCourseDto(course));
    }
}
