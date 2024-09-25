package vn.hcmute.elearning.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.hcmute.elearning.dtos.course.response.CmsGetDetailCourseResponse;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.model.course.CourseDto;

import java.util.Optional;
import java.util.Set;

public interface CourseRepository extends JpaRepository<Course, String>, JpaSpecificationExecutor<Course> {
    Page<Course> findCourseEntitiesByUsersIn(@Param("user_instances")Set<User> user, Pageable pageable);
    @Query(
        "SELECT new vn.hcmute.elearning.model.course.CourseDto(c.id, c.courseName, c.description, c.price, c.imageUrl, c.status, c.approveStatus, c.createDate, t.id, CONCAT(ut.firstName, ' ', ut.lastName), COUNT(u.id), c.discountPercentage, c.price * (100 - c.discountPercentage) / 100, 0, COALESCE(avg(r.star), 0)) "
            + "FROM Course c " +
            "LEFT JOIN Teacher t ON c.teacher.id = t.id " +
            "LEFT JOIN User ut ON t.user.id = ut.id " +
            "LEFT JOIN Review r on r.course.id = c.id "
            + "LEFT JOIN c.users u "
            + "WHERE c.status = 'ACTIVE' AND c.approveStatus = 'APPROVE' AND c.id NOT IN (SELECT c1.id FROM Course c1 INNER JOIN c1.users u1 WHERE u1.id = :userId) " +
            "GROUP BY c.id "
    )
    Page<CourseDto> findCourseUserNotRegister(@Param("userId") String userId, Pageable pageable);

    @Query(
        "SELECT new vn.hcmute.elearning.model.course.CourseDto(c.id, c.courseName, c.description, c.price, c.imageUrl, c.status, c.approveStatus, c.createDate, t.id, CONCAT(ut.firstName, ' ', ut.lastName), COUNT(u.id), c.discountPercentage, c.price * (100 - c.discountPercentage) / 100, 0 , COALESCE(avg(r.star), 0)) "
            + "FROM Course c " +
            "LEFT JOIN Teacher t ON c.teacher.id = t.id " +
            "LEFT JOIN User ut ON t.user.id = ut.id "
            + "LEFT JOIN Review r on r.course.id = c.id "
            + "JOIN c.users u "
            + "WHERE u.id IS NULL AND c.status = 'ACTIVE' AND c.approveStatus = 'APPROVE' OR u.id = :userId " +
            "GROUP BY c.id"
    )
    Page<CourseDto> findCourseUserRegister(@Param("userId") String userId, Pageable pageable);

    @Query(value = "select new vn.hcmute.elearning.dtos.course.response.CmsGetDetailCourseResponse(" +
        "c.id, c.courseName, c.description, c.price, c.imageUrl, c.status, c.approveStatus, c.createDate, " +
        "u.firstName, u.lastName, u.birthday, u.gender, u.email, u.phone, u.avatar)" +
        "from Course c left join Teacher tc on c.teacher.id = tc.id left join User u on u.id = tc.user.id where c.id =:id")
    CmsGetDetailCourseResponse getDetailCourseByTeacher(String id);

    @Query(value = "select * from course left join course_user on course.id = course_user.course_id " +
        "where (course_user.user_id = :userId or course.created_by = :userId) and course.id = :courseId", nativeQuery = true)
    Course getCourseByTeacherOrUserId(String courseId, String userId);

    @Query("select c from Exam  e inner join e.lesson l inner join l.course c where e.id = :examId")
    Optional<Course> getCourseByExamId(String examId);

}
