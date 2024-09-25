package vn.hcmute.elearning.handler.statistic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.statistic.request.TeacherGetCardsInfoRequest;
import vn.hcmute.elearning.dtos.statistic.response.TeacherGetCardsInfoResponse;
import vn.hcmute.elearning.entity.Teacher;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.IStatisticService;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Component
@RequiredArgsConstructor
@Slf4j
public class TeacherGetCardsInfoHandler extends RequestHandler<TeacherGetCardsInfoRequest, TeacherGetCardsInfoResponse> {

    private final IStatisticService statisticService;
    private final IUserService userService;

    @Override
    public TeacherGetCardsInfoResponse handle(TeacherGetCardsInfoRequest request) {
        User user = userService.getUserById(request.getUserId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        Teacher teacher = user.getTeacher();
        if (teacher == null) {
            throw new InternalException(ResponseCode.USER_INVALID);
        }

        return statisticService.teacherGetCardsInfo(teacher.getId(), request.getFromDate(), request.getToDate());
    }
}
