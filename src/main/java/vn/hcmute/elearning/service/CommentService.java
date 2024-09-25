package vn.hcmute.elearning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.hcmute.elearning.entity.Comment;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.repository.CommentRepository;
import vn.hcmute.elearning.service.interfaces.ICommentService;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {
    private final CommentRepository commentRepository;
    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment getByIdNotNull(String id) {
        return commentRepository.findById(id)
            .orElseThrow(() -> new InternalException(ResponseCode.COMMENT_NOT_FOUND));
    }

    @Override
    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }
}
