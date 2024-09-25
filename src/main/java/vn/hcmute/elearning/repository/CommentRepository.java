package vn.hcmute.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.hcmute.elearning.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, String>, JpaSpecificationExecutor<Comment> {
}
