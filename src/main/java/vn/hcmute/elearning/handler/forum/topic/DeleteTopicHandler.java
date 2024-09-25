package vn.hcmute.elearning.handler.forum.topic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.handler.IDeleteModelHandler;
import vn.hcmute.elearning.dtos.forum.topic.request.DeleteTopicRequest;
import vn.hcmute.elearning.entity.forum.ForumTopic;
import vn.hcmute.elearning.repository.forum.ForumTopicRepository;

@Getter
@Component
@RequiredArgsConstructor
public class DeleteTopicHandler implements IDeleteModelHandler<Long, DeleteTopicRequest, ForumTopic> {

    private final ForumTopicRepository repository;

    @Override
    public void validate(ForumTopic entity, DeleteTopicRequest request) {
        if (!entity.getCreatedBy().equals(request.getUserId())) {
            throw notFound();
        }
    }

}
