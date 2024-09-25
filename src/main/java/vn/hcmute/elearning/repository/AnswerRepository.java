package vn.hcmute.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hcmute.elearning.entity.Answer;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, String> {
    List<Answer> findAllByCode(String code);
}
