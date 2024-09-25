package vn.hcmute.elearning.handler.course;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.course.request.GetListCoursesRequest;
import vn.hcmute.elearning.dtos.course.response.GetListCoursesResponse;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.enums.ApproveStatus;
import vn.hcmute.elearning.enums.CourseStatus;
import vn.hcmute.elearning.mapper.ICourseMapper;
import vn.hcmute.elearning.model.course.CourseDto;
import vn.hcmute.elearning.service.interfaces.ICourseService;
import vn.hcmute.elearning.service.specifications.CourseSpecification;
import vn.hcmute.elearning.utils.Paginate;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetListCourseHandler extends RequestHandler<GetListCoursesRequest, GetListCoursesResponse> {

    private final CourseSpecification courseSpecification;

    private final ICourseService courseService;
    private final ICourseMapper courseMapper;

    @Override
    public GetListCoursesResponse handle(GetListCoursesRequest request) {
        Specification<Course> specification = Specification.where(courseSpecification.likeCourseName(request.getCourseName()))
                .and(courseSpecification.gteCreateFrom(request.fromDatetime(request.getFromDate())))
                .and(courseSpecification.lteCreateTo(request.toDatetime(request.getToDate())))
                .and(courseSpecification.equalStatus(CourseStatus.ACTIVE))
                .and(courseSpecification.equalApproveStatus(ApproveStatus.APPROVE));
        Page<Course> courseEntityPage = courseService.getListCourses(specification, request.getPageable());

        List<CourseDto> courseDtoList = new ArrayList<>();
        courseEntityPage.get().forEach(course -> {
            CourseDto courseDto = courseMapper.toCourseDto(course);
            courseDto.setCurrentPrice(course.getPrice() * (100 - course.getDiscountPercentage()) / 100);
            courseDtoList.add(courseDto);
        });
        return new GetListCoursesResponse(courseDtoList, new Paginate(courseEntityPage));
    }
}
