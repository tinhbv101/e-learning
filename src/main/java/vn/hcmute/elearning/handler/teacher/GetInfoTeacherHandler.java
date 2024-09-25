package vn.hcmute.elearning.handler.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.teacher.request.GetInfoTeacherRequest;
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
public class GetInfoTeacherHandler extends RequestHandler<GetInfoTeacherRequest, GetInfoTeacherResponse> {
    private final ITeacherService teacherService;
    private final ITeacherMapper teacherMapper;

    @Override
    public GetInfoTeacherResponse handle(GetInfoTeacherRequest request) {
        Teacher teacher = teacherService.getByUserId(request.getUserId());
        if (teacher == null) {
            throw new InternalException(ResponseCode.TEACHER_NOT_FOUND);
        }
        TeacherDto teacherDto = teacherMapper.toTeacherDto(teacher);
        return new GetInfoTeacherResponse(teacherDto);
    }
}
