package vn.hcmute.elearning.handler.forum.topic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.handler.IUpdateModelHandler;
import vn.hcmute.elearning.dtos.forum.topic.request.UpdateTopicRequest;
import vn.hcmute.elearning.entity.forum.ForumTopic;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.forum.ForumTopicMapper;
import vn.hcmute.elearning.repository.forum.ForumTopicRepository;

@Getter
@Component
@RequiredArgsConstructor
public class UpdateTopicHandler implements IUpdateModelHandler<Long, UpdateTopicRequest, ForumTopic, ForumTopicMapper> {

    private final ForumTopicRepository repository;
    private final ForumTopicMapper mapper;

    @Override
    public void validate(ForumTopic entity, UpdateTopicRequest request) {
        if (!entity.getCreatedBy().equals(request.getUserId())) {
            throw notFound();
        }
    }

    @Override
    public InternalException notFound() {
        return new InternalException(ResponseCode.FORUM_TOPIC_NOT_FOUND);
    }
}
