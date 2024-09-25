package vn.hcmute.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hcmute.elearning.entity.FillCorrect;

import java.util.List;
import java.util.Set;

public interface FillCorrectRepository extends JpaRepository<FillCorrect, String>{
    Set<FillCorrect> findAllByIdIn(List<String> ids);
    Set<FillCorrect> findAllByQuestionId(String questionId);
    void deleteAllByQuestionId(String questionId);
}
