package vn.hcmute.elearning.handler.course;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.course.request.GetUsersByCourseRequest;
import vn.hcmute.elearning.dtos.course.response.GetUsersByCourseResponse;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.IUserMapper;
import vn.hcmute.elearning.service.CourseService;
import vn.hcmute.elearning.utils.Paginate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetUserByCourseHandler extends RequestHandler<GetUsersByCourseRequest, GetUsersByCourseResponse> {

    private final CourseService courseService;
    private final IUserMapper iUserMapper;

    @Override
    public GetUsersByCourseResponse handle(GetUsersByCourseRequest request) {
        Course course = courseService.getCourseById(request.getCourseId());
        if (Objects.isNull(course)) {
            throw new InternalException(ResponseCode.COURSE_NOT_FOUND);
        }
        List<User> users = new ArrayList<>(course.getUsers());
        int start = (int) request.getPageable().getOffset();
        int end = Math.min((start + request.getPageable().getPageSize()), users.size());
        Page<User> page = null;
        if(start > users.size())
             page = new PageImpl<>(new ArrayList<>(), request.getPageable(), users.size());
        else
            page = new PageImpl<>(users.subList(start, end), request.getPageable(), users.size());
        return new GetUsersByCourseResponse(iUserMapper.toListUserDto(page.getContent()), new Paginate(page));
    }
}
