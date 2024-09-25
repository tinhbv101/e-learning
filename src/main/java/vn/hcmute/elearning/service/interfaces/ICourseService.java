package vn.hcmute.elearning.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import vn.hcmute.elearning.dtos.course.response.CmsGetDetailCourseResponse;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.PaymentType;
import vn.hcmute.elearning.model.course.CourseDto;
import vn.hcmute.elearning.service.course.CourseRegisterStrategy;

public interface ICourseService {
    Page<Course> getListCourses(Specification<Course> specification, Pageable pageable);

    Course getCourseById(String id);

    Course getCourseByIdNotNull(String id);

    Course save(Course course);

    Course updateCourse(Course course);

    void deleteCourseById(String id);

    Page<CourseDto> getCourseByUser(User user, Pageable pageable);
    Page<CourseDto> getUnregisterCourseByUser(User user, Pageable pageable);

    CmsGetDetailCourseResponse getDetailByTeacher(String id);

    Course getByTeacherOrUser(String courseId, String userId);

    Course getCourseByExamId(String examId);

    CourseRegisterStrategy getRegisterStrategy(PaymentType paymentType);
}
