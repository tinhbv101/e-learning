package vn.hcmute.elearning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.hcmute.elearning.dtos.course.response.CmsGetDetailCourseResponse;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.PaymentType;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.model.course.CourseDto;
import vn.hcmute.elearning.repository.CourseRepository;
import vn.hcmute.elearning.service.course.CourseRegisterStrategy;
import vn.hcmute.elearning.service.interfaces.ICourseService;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
@RequiredArgsConstructor
public class CourseService implements ICourseService, InitializingBean {

    private final List<CourseRegisterStrategy> courseRegisterStrategies;
    private final ConcurrentMap<PaymentType, CourseRegisterStrategy> strategyConcurrentMap = new ConcurrentHashMap<>();
    private final CourseRepository courseRepository;

    @Override
    public Page<Course> getListCourses(Specification<Course> specification, Pageable pageable) {
        return courseRepository.findAll(specification, pageable);
    }

    @Override
    public Course getCourseById(String id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public Course getCourseByIdNotNull(String id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new InternalException(ResponseCode.COURSE_NOT_FOUND));
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourseById(String id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Page<CourseDto> getCourseByUser(User user, Pageable pageable) {
        return courseRepository.findCourseUserRegister(user.getId(), pageable);
    }

    @Override
    public Page<CourseDto> getUnregisterCourseByUser(User user, Pageable pageable) {
        return courseRepository.findCourseUserNotRegister(user.getId(), pageable);
    }

    @Override
    public CmsGetDetailCourseResponse getDetailByTeacher(String id) {
        return courseRepository.getDetailCourseByTeacher(id);
    }

    @Override
    public Course getByTeacherOrUser(String courseId, String userId) {
        return courseRepository.getCourseByTeacherOrUserId(courseId, userId);
    }

    @Override
    public Course getCourseByExamId(String examId) {
        return courseRepository.getCourseByExamId(examId)
                .orElseThrow(() -> new InternalException(ResponseCode.COURSE_NOT_FOUND));
    }

    @Override
    public void afterPropertiesSet() {
        for (CourseRegisterStrategy strategy : courseRegisterStrategies) {
            strategyConcurrentMap.put(strategy.getPaymentType(), strategy);
        }
    }

    @Override
    public CourseRegisterStrategy getRegisterStrategy(PaymentType paymentType) {
        CourseRegisterStrategy registerStrategy = strategyConcurrentMap.get(paymentType);
        if (registerStrategy != null) {
            return registerStrategy;
        }
        throw new IllegalArgumentException();
    }

}
