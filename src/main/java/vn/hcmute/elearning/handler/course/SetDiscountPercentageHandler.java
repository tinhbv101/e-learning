package vn.hcmute.elearning.handler.course;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.course.request.SetDiscountPercentageRequest;
import vn.hcmute.elearning.dtos.course.response.UpdateCourseResponse;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.ICourseMapper;
import vn.hcmute.elearning.model.course.CourseDto;
import vn.hcmute.elearning.service.interfaces.ICourseService;

@Component
@RequiredArgsConstructor
public class SetDiscountPercentageHandler extends RequestHandler<SetDiscountPercentageRequest, UpdateCourseResponse> {

    private final ICourseService courseService;
    private final ICourseMapper courseMapper;

    @Override
    public UpdateCourseResponse handle(SetDiscountPercentageRequest request) {
        Course course = courseService.getCourseById(request.getCourseId());
        if (course == null) {
            throw new InternalException(ResponseCode.COURSE_NOT_FOUND);
        }
        course.setDiscountPercentage(request.getDiscountPercentage());
        course = courseService.save(course);
        CourseDto courseDto = courseMapper.toCourseDto(course);
        courseDto.setCurrentPrice(course.getPrice() * (100 - course.getDiscountPercentage()) / 100);
        return new UpdateCourseResponse(courseDto);
    }
}
