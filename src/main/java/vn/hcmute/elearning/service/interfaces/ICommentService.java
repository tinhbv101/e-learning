package vn.hcmute.elearning.service.interfaces;

import vn.hcmute.elearning.entity.Comment;

public interface ICommentService {
    Comment save(Comment comment);
    Comment getByIdNotNull(String id);
    void deleteById(String id);
}
