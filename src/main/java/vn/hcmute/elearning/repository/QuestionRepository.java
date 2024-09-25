package vn.hcmute.elearning.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.hcmute.elearning.entity.Exam;
import vn.hcmute.elearning.entity.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, String>, JpaSpecificationExecutor<Question> {
    void deleteAllByExam(Exam exam);

    List<Question> findByExam(Exam exam);
    Page<Question> findByExam(Exam exam, Pageable pageable);
}
