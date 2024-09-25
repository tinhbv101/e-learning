package vn.hcmute.elearning.handler.teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.teacher.request.CreateRequestRoleTeacherRequest;
import vn.hcmute.elearning.dtos.teacher.response.CreateRequestRoleTeacherResponse;
import vn.hcmute.elearning.entity.Ekyc;
import vn.hcmute.elearning.entity.Teacher;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.enums.TeacherStatus;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.ITeacherMapper;
import vn.hcmute.elearning.service.interfaces.IEkycService;
import vn.hcmute.elearning.service.interfaces.ITeacherService;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Component
@RequiredArgsConstructor
public class CreateRequestRoleTeacherHandler extends RequestHandler<CreateRequestRoleTeacherRequest, CreateRequestRoleTeacherResponse> {
    private final IEkycService ekycService;
    private final IUserService userService;
    private final ITeacherService teacherService;
    private final ITeacherMapper teacherMapper;

    @Override
    public CreateRequestRoleTeacherResponse handle(CreateRequestRoleTeacherRequest request) {
        User user = userService.getUserById(request.getUserId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        Ekyc ekyc = ekycService.getById(request.getEkycId());
        if (ekyc == null) {
            throw new InternalException(ResponseCode.USER_NOT_AUTHENTICATED_OCR);
        }
        Teacher teacher = teacherService.getByUserId(request.getUserId());
        if (teacher != null) {
            if ((TeacherStatus.ACTIVE.equals(teacher.getStatus()) || TeacherStatus.BAN.equals(teacher.getStatus()))) {
                throw new InternalException(ResponseCode.TEACHER_REQUEST_APPROVED);
            }
        } else {
            teacher = new Teacher().setEkyc(ekyc).setUser(user);
        }

        teacher.setStatus(TeacherStatus.CREATE);
        Teacher teacherSave = teacherService.save(teacher);
        user.setTeacher(teacherSave);
        user.setIsOrc(true);
        userService.updateUser(user);
        return new CreateRequestRoleTeacherResponse(teacherMapper.toTeacherDto(teacherSave));
    }
}
