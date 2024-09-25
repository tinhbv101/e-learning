package vn.hcmute.elearning.handler.forum.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.handler.IUpdateModelHandler;
import vn.hcmute.elearning.dtos.forum.post.request.UpdatePostRequest;
import vn.hcmute.elearning.entity.forum.ForumPost;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.forum.ForumPostMapper;
import vn.hcmute.elearning.repository.forum.ForumPostRepository;

@Getter
@Component
@RequiredArgsConstructor
public class UpdatePostHandler implements IUpdateModelHandler<String, UpdatePostRequest, ForumPost, ForumPostMapper> {

    private final ForumPostRepository repository;
    private final ForumPostMapper mapper;

    @Override
    public void validate(ForumPost entity, UpdatePostRequest request) {
        if (!entity.getCreatedBy().equals(request.getUserId())) {
            throw notFound();
        }
    }

    @Override
    public InternalException notFound() {
        return new InternalException(ResponseCode.FORUM_POST_NOT_FOUND);
    }
}
