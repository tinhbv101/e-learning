package vn.hcmute.elearning.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import vn.hcmute.elearning.entity.Lesson;
import vn.hcmute.elearning.enums.DisplayStatus;

import java.util.List;

public interface ILessonService {
    List<Lesson> getLessonsByCourse(String courseId);
    Page<Lesson> getAll(Specification<Lesson> specification, Pageable pageable);
    Lesson getById(String id);
    Lesson getByIdAndCreateBy(String id, String userId);
    Lesson save(Lesson lesson);
    void deleteById(String lessonId);
    List<Lesson> getLessonByCourseIdAndStatus(String courseId, DisplayStatus displayStatus);
}
