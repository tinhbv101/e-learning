package vn.hcmute.elearning.handler.course;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.course.request.GetCourseByUserIdRequest;
import vn.hcmute.elearning.dtos.course.response.GetCourseByUserIdResponse;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.model.course.CourseDto;
import vn.hcmute.elearning.service.interfaces.ICourseService;
import vn.hcmute.elearning.service.interfaces.IProgressService;
import vn.hcmute.elearning.service.interfaces.IUserService;
import vn.hcmute.elearning.utils.Paginate;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetCourseHandler extends RequestHandler<GetCourseByUserIdRequest, GetCourseByUserIdResponse> {
    private final ICourseService courseService;
    private final IUserService userService;
    private final IProgressService progressService;

    @Override
    public GetCourseByUserIdResponse handle(GetCourseByUserIdRequest request) {
        User user = userService.getUserById(request.getUserId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        Page<CourseDto> page = courseService.getCourseByUser(user, request.getPageable());
        List<CourseDto> list = page.getContent().parallelStream().peek(courseDto -> courseDto.setProgressPercent(progressService.getProgressOfCourse(courseDto.getId(), user.getId()))).collect(Collectors.toList());
        return new GetCourseByUserIdResponse(list, new Paginate(page));
    }
}
