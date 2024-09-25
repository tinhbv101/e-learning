package vn.hcmute.elearning.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import vn.hcmute.elearning.entity.Teacher;
import vn.hcmute.elearning.model.teacher.TeacherDto;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface ITeacherMapper {
    TeacherDto toTeacherDto(Teacher teacher);

    List<TeacherDto> toListTeacherDto(List<Teacher> teachers);
}
