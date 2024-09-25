package vn.hcmute.elearning.handler.teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.teacher.request.TeacherGetBalanceRequest;
import vn.hcmute.elearning.dtos.teacher.response.TeacherGetBalanceResponse;
import vn.hcmute.elearning.entity.Teacher;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.ITeacherService;


@Component
@RequiredArgsConstructor
public class TeacherGetBalanceHandler extends RequestHandler<TeacherGetBalanceRequest, TeacherGetBalanceResponse> {
    private final ITeacherService teacherService;

    @Override
    public TeacherGetBalanceResponse handle(TeacherGetBalanceRequest request) {
        Teacher teacher = teacherService.getByUserId(request.getUserId());
        if (teacher == null) {
            throw new InternalException(ResponseCode.TEACHER_NOT_FOUND);
        }
        return new TeacherGetBalanceResponse(teacher.getBalance());
    }
}
