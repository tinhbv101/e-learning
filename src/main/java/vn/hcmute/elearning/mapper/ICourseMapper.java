package vn.hcmute.elearning.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.model.course.CourseDto;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface ICourseMapper {
    CourseDto toCourseDto(Course course);
}
