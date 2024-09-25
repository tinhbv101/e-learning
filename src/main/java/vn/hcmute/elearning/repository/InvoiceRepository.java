package vn.hcmute.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.entity.Invoice;
import vn.hcmute.elearning.entity.User;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, String>, JpaSpecificationExecutor<Invoice> {

    Boolean existsByUserAndCourse(User user, Course course);
    Optional<Invoice> findByCode(String code);
    Boolean existsByCode(String code);
    Optional<Invoice> findFirstByUserIdAndCourseId(String userId, String courseId);

}
