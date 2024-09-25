package vn.hcmute.elearning.handler.course;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.course.request.SettingCourseRequest;
import vn.hcmute.elearning.dtos.course.response.SettingCourseResponse;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.ICourseMapper;
import vn.hcmute.elearning.model.course.CourseDto;
import vn.hcmute.elearning.service.interfaces.ICourseService;

@Component
@RequiredArgsConstructor
@Slf4j
public class SettingCourseHandler extends RequestHandler<SettingCourseRequest, SettingCourseResponse> {
    private final ICourseService courseService;
    private final ICourseMapper courseMapper;

    @Override
    public SettingCourseResponse handle(SettingCourseRequest request) {
        Course course = courseService.getCourseById(request.getId());
        if (course == null) {
            throw new InternalException(ResponseCode.COURSE_NOT_FOUND);
        }
        course.setStatus(request.getStatus());

        Course courseSave = courseService.save(course);
        CourseDto courseDto = courseMapper.toCourseDto(courseSave);
        return new SettingCourseResponse(courseDto);
    }
}
