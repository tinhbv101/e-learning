package vn.hcmute.elearning.handler.course;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.course.request.UnBlockCourseRequest;
import vn.hcmute.elearning.dtos.course.response.UnblockCourseResponse;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.enums.ApproveStatus;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.ICourseMapper;
import vn.hcmute.elearning.model.course.CourseDto;
import vn.hcmute.elearning.service.interfaces.ICourseService;

@Component
@RequiredArgsConstructor
@Slf4j
public class UnBlockCourseHandler extends RequestHandler<UnBlockCourseRequest, UnblockCourseResponse> {
    private final ICourseService iCourseService;
    private final ICourseMapper courseMapper;

    @Override
    public UnblockCourseResponse handle(UnBlockCourseRequest request) {
        Course course = iCourseService.getCourseById(request.getId());
        if (course == null) {
            throw new InternalException(ResponseCode.COURSE_NOT_FOUND);
        }
        course.setApproveStatus(ApproveStatus.WAITING);

        Course courseSave = iCourseService.save(course);
        CourseDto courseDto = courseMapper.toCourseDto(courseSave);
        return new UnblockCourseResponse(courseDto);
    }
}
