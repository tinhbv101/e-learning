package vn.hcmute.elearning.handler.forum.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.handler.ICreateModelHandler;
import vn.hcmute.elearning.dtos.forum.post.request.CreatePostRequest;
import vn.hcmute.elearning.entity.forum.ForumPost;
import vn.hcmute.elearning.entity.forum.ForumTopic;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.forum.ForumPostMapper;
import vn.hcmute.elearning.repository.forum.ForumPostRepository;
import vn.hcmute.elearning.service.forum.ForumTopicService;

@Getter
@Component
@RequiredArgsConstructor
public class CreatePostHandler implements ICreateModelHandler<String, CreatePostRequest, ForumPost, ForumPostMapper> {


    private final ForumPostRepository repository;
    private final ForumPostMapper mapper;
    private final ForumTopicService forumTopicService;

    @Override
    public void postMapping(ForumPost entity, CreatePostRequest request) {
        ForumTopic topic = forumTopicService.findByIdNonNull(request.getTopicId());
        entity.setTopic(topic);
        entity.setOrdinal(repository.countByTopicId(request.getTopicId()) + 1);
    }

    @Override
    public InternalException notFound() {
        return new InternalException(ResponseCode.FORUM_POST_NOT_FOUND);
    }
}
