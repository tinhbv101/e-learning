package vn.hcmute.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.hcmute.elearning.entity.DocumentProgress;

public interface DocumentProgressRepository extends JpaRepository<DocumentProgress, String> {
    void deleteByDocumentIdAndUserId(String documentId, String userId);

    boolean existsByDocumentIdAndUserId(String documentId, String userId);

    @Query("select count(dp.id) " +
        "from DocumentProgress dp " +
        "inner join dp.document d " +
        "inner join d.lesson l " +
        "inner join l.course c " +
        "where c.id = :courseId and dp.user.id = :userId and d.displayStatus = 'VISIBLE' and l.displayStatus = 'VISIBLE'")
    long countByCourseIdAndUserId(String courseId, String userId);
}
