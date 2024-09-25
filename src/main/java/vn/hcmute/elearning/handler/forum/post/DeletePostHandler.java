package vn.hcmute.elearning.handler.forum.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.handler.IDeleteModelHandler;
import vn.hcmute.elearning.dtos.forum.post.request.DeletePostRequest;
import vn.hcmute.elearning.entity.forum.ForumPost;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.repository.forum.ForumPostRepository;

@Getter
@Component
@RequiredArgsConstructor
public class DeletePostHandler implements IDeleteModelHandler<String, DeletePostRequest, ForumPost> {

    private final ForumPostRepository repository;

    @Override
    public void validate(ForumPost entity, DeletePostRequest request) {
        if (!entity.getCreatedBy().equals(request.getUserId())) {
            throw notFound();
        }
    }

    @Override
    public InternalException notFound() {
        return new InternalException(ResponseCode.FORUM_POST_NOT_FOUND);
    }
}
