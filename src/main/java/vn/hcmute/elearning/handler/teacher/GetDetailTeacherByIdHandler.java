package vn.hcmute.elearning.handler.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.teacher.request.GetDetailTeacherByIdRequest;
import vn.hcmute.elearning.dtos.teacher.response.GetInfoTeacherResponse;
import vn.hcmute.elearning.entity.Teacher;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.ITeacherMapper;
import vn.hcmute.elearning.model.teacher.TeacherDto;
import vn.hcmute.elearning.service.interfaces.ITeacherService;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetDetailTeacherByIdHandler extends RequestHandler<GetDetailTeacherByIdRequest, GetInfoTeacherResponse> {
    private final ITeacherService iTeacherService;
    private final ITeacherMapper teacherMapper;

    @Override
    public GetInfoTeacherResponse handle(GetDetailTeacherByIdRequest request) {
        Teacher teacher = iTeacherService.getById(request.getId());
        if (teacher == null) {
            throw new InternalException(ResponseCode.TEACHER_NOT_FOUND);
        }
        TeacherDto teacherDto = teacherMapper.toTeacherDto(teacher);
        return new GetInfoTeacherResponse(teacherDto);
    }
}
