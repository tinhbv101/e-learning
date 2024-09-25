package vn.hcmute.elearning.handler.course;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.course.request.BlockCourseRequest;
import vn.hcmute.elearning.dtos.course.response.BlockCourseResponse;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.enums.ApproveStatus;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.ICourseMapper;
import vn.hcmute.elearning.model.course.CourseDto;
import vn.hcmute.elearning.service.interfaces.ICourseService;

@Component
@RequiredArgsConstructor
public class BlockCourseHandler extends RequestHandler<BlockCourseRequest, BlockCourseResponse> {

    private final ICourseService courseService;
    private final ICourseMapper courseMapper;

    @Override
    public BlockCourseResponse handle(BlockCourseRequest request) {
        Course course = courseService.getCourseById(request.getId());
        if (course == null) {
            throw new InternalException(ResponseCode.COURSE_NOT_FOUND);
        }
        course.setApproveStatus(ApproveStatus.BLOCK);

        Course courseSave = courseService.save(course);
        CourseDto courseDto = courseMapper.toCourseDto(courseSave);
        return new BlockCourseResponse(courseDto);
    }
}
