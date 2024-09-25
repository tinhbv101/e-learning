package vn.hcmute.elearning.service.forum;

import org.springframework.stereotype.Service;
import vn.hcmute.elearning.entity.forum.ForumTopic;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.repository.forum.ForumTopicRepository;
import vn.hcmute.elearning.service.BaseService;

@Service
public class ForumTopicService extends BaseService<Long, ForumTopic, ForumTopicRepository> {

    public ForumTopicService(ForumTopicRepository repository) {
        super(repository);
    }

    public ForumTopic getLatestPostByCategoryId(String categoryId) {
        return repository.getLatestPostByCategoryId(categoryId);
    }

    @Override
    protected ResponseCode notFound() {
        return ResponseCode.FORUM_TOPIC_NOT_FOUND;
    }
}
