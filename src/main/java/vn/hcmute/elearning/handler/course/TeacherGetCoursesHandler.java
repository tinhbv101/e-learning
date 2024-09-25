package vn.hcmute.elearning.handler.course;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.course.request.TeacherGetCoursesRequest;
import vn.hcmute.elearning.dtos.course.response.GetListCoursesResponse;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.mapper.ICourseMapper;
import vn.hcmute.elearning.model.course.CourseDto;
import vn.hcmute.elearning.service.interfaces.ICourseService;
import vn.hcmute.elearning.service.specifications.CourseSpecification;
import vn.hcmute.elearning.utils.Paginate;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TeacherGetCoursesHandler extends RequestHandler<TeacherGetCoursesRequest, GetListCoursesResponse> {
    private final CourseSpecification courseSpecification;
    private final ICourseService courseService;
    private final ICourseMapper courseMapper;

    @Override
    public GetListCoursesResponse handle(TeacherGetCoursesRequest request) {
        Specification<Course> specification = Specification.where(courseSpecification.equalCreateBy(request.getUserId()))
                .and(courseSpecification.equalCourseId(request.getCourseId()))
                .and(courseSpecification.likeCourseName(request.getCourseName()))
                .and(courseSpecification.gteCreateFrom(request.fromDatetime(request.getFromDate())))
                .and(courseSpecification.lteCreateTo(request.toDatetime(request.getToDate())))
                .and(courseSpecification.equalStatus(request.getStatus()))
                .and(courseSpecification.equalApproveStatus(request.getApproveStatus()));
        Page<Course> courses = courseService.getListCourses(specification, request.getPageable());

        List<CourseDto> courseDtoList = new ArrayList<>();
        courses.getContent().forEach(course -> {
            CourseDto courseDto = courseMapper.toCourseDto(course);
            courseDto.setCurrentPrice(course.getPrice() * (100 - course.getDiscountPercentage()) / 100);
            courseDtoList.add(courseDto);
        });
        return new GetListCoursesResponse(courseDtoList, new Paginate(courses));
    }
}
