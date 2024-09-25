package vn.hcmute.elearning.handler.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.user.request.GetUnregisterCourseRequest;
import vn.hcmute.elearning.dtos.user.response.GetUnregisterCourseResponse;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.model.course.CourseDto;
import vn.hcmute.elearning.service.interfaces.ICourseService;
import vn.hcmute.elearning.service.interfaces.IUserService;
import vn.hcmute.elearning.utils.Paginate;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetUnregisterCourseHandler extends RequestHandler<GetUnregisterCourseRequest, GetUnregisterCourseResponse> {

    private final ICourseService courseService;
    private final IUserService userService;

    @Override
    public GetUnregisterCourseResponse handle(GetUnregisterCourseRequest request) {
        User user = userService.getUserById(request.getUserId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        Page<CourseDto> page = courseService.getUnregisterCourseByUser(user, request.getPageable());
        return new GetUnregisterCourseResponse(page.getContent(), new Paginate(page));
    }

}
