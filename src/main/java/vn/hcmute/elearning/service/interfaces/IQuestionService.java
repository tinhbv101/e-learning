package vn.hcmute.elearning.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import vn.hcmute.elearning.entity.Exam;
import vn.hcmute.elearning.entity.Question;

import java.util.List;

public interface IQuestionService {
    Question createQuestion(Question question);

    void deleteQuestionByExam(Exam exam);

    Question getQuestionById(String id);

    Question updateQuestion(Question question);

    void deleteQuestionById(String id);

    Page<Question> getQuestion(Specification<Question> specification, Pageable pageable);

    List<Question> getAllQuestionByExam(Exam exam);
    Page<Question> getPageQuestionByExam(Exam exam, Pageable pageable);
    Question save(Question question);
}
