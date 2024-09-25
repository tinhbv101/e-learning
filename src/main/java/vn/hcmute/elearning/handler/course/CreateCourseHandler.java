package vn.hcmute.elearning.handler.course;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.course.request.CreateCourseRequest;
import vn.hcmute.elearning.dtos.course.response.CreateCourseResponse;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.entity.Teacher;
import vn.hcmute.elearning.enums.ApproveStatus;
import vn.hcmute.elearning.enums.CourseStatus;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.ICourseMapper;
import vn.hcmute.elearning.model.course.CourseDto;
import vn.hcmute.elearning.service.MinIOService;
import vn.hcmute.elearning.service.interfaces.ICourseService;
import vn.hcmute.elearning.service.interfaces.ITeacherService;
import vn.hcmute.elearning.utils.CommonUtils;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateCourseHandler extends RequestHandler<CreateCourseRequest, CreateCourseResponse> {

    private final ICourseService courseService;
    private final MinIOService minIOService;
    private final ICourseMapper courseMapper;
    private final ITeacherService iTeacherService;

    @Override
    public CreateCourseResponse handle(CreateCourseRequest request) {
        MultipartFile courseImage = request.getCourseImage();
        String objectName = CommonUtils.fileNameTimestamp(courseImage.getOriginalFilename());
        try {
            objectName = minIOService.uploadFile(objectName, courseImage.getBytes(), courseImage.getContentType(), true);
        } catch (Exception ex) {
            log.error("upload file to service minio error: {}", ex.getMessage());
        }
        Teacher teacher = iTeacherService.getByUserId(request.getUserId());
        if (teacher == null) {
            throw new InternalException(ResponseCode.TEACHER_NOT_FOUND);
        }
        Course course = new Course()
            .setCourseName(request.getCourseName())
            .setPrice(request.getPrice())
            .setDescription(request.getDescription())
            .setImageUrl(objectName)
            .setApproveStatus(ApproveStatus.WAITING)
            .setTeacher(teacher)
            .setDiscountPercentage(0)
            .setStatus(CourseStatus.INACTIVE);
        Course courseCreate = courseService.save(course);
        CourseDto courseDto = courseMapper.toCourseDto(courseCreate);
        courseDto.setCurrentPrice(course.getPrice() * (100 - course.getDiscountPercentage()) / 100);
        return new CreateCourseResponse(courseDto);
    }
}
