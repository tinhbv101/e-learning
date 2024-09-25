package vn.hcmute.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.hcmute.elearning.entity.Lesson;
import vn.hcmute.elearning.enums.DisplayStatus;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, String>, JpaSpecificationExecutor<Lesson> {
    List<Lesson> findAllByCourseIdOrderByCreateDate(String id);
    void deleteById(String id);
    Lesson findByIdAndCreatedBy(String id, String userId);
    List<Lesson> findAllByCourseIdAndDisplayStatusOrderByCreateDate(String courseId, DisplayStatus displayStatus);
}
