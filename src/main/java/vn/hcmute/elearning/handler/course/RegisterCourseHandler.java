package vn.hcmute.elearning.handler.course;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.course.request.RegisterCourseRequest;
import vn.hcmute.elearning.dtos.course.response.RegisterCourseResponse;
import vn.hcmute.elearning.service.course.CourseRegisterStrategy;
import vn.hcmute.elearning.service.interfaces.ICourseService;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegisterCourseHandler extends RequestHandler<RegisterCourseRequest, RegisterCourseResponse> {

    private final ICourseService courseService;

    @Override
    public RegisterCourseResponse handle(RegisterCourseRequest request) {
        CourseRegisterStrategy strategy = courseService.getRegisterStrategy(request.getPaymentType());
        return new RegisterCourseResponse(strategy.create(request.getCourseId(), request.getUserId()));
    }
}
