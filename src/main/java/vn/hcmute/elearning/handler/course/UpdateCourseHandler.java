package vn.hcmute.elearning.handler.course;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.course.request.UpdateCourseRequest;
import vn.hcmute.elearning.dtos.course.response.UpdateCourseResponse;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.ICourseMapper;
import vn.hcmute.elearning.model.course.CourseDto;
import vn.hcmute.elearning.service.MinIOService;
import vn.hcmute.elearning.service.interfaces.ICourseService;
import vn.hcmute.elearning.utils.CommonUtils;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateCourseHandler extends RequestHandler<UpdateCourseRequest, UpdateCourseResponse> {

    private final ICourseService courseService;
    private final MinIOService minIOService;
    private final ICourseMapper courseMapper;

    @Override
    public UpdateCourseResponse handle(UpdateCourseRequest request) {
        Course course = courseService.getCourseById(request.getId());
        if (course == null) {
            throw new InternalException(ResponseCode.COURSE_NOT_FOUND);
        }
        MultipartFile courseImage = request.getCourseImage();
        if (courseImage != null) {
            String objectName = CommonUtils.fileNameTimestamp(courseImage.getOriginalFilename());
            try {
                objectName = minIOService.uploadFile(objectName, courseImage.getBytes(), courseImage.getContentType(), true);
            } catch (Exception ex) {
                log.error("upload file to service minio error: {}", ex.getMessage());
            }
            course.setImageUrl(objectName);
        }
        course.setCourseName(request.getCourseName());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        if (request.getDiscountPercentage() != null) {
            course.setDiscountPercentage(request.getDiscountPercentage());
        }
        Course courseSave = courseService.save(course);

        CourseDto courseDto = courseMapper.toCourseDto(courseSave);
        courseDto.setCurrentPrice(course.getPrice() * (100 - course.getDiscountPercentage()) / 100);
        return new UpdateCourseResponse(courseDto);
    }
}
