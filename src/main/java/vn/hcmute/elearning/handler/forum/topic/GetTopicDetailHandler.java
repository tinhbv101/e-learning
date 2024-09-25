package vn.hcmute.elearning.handler.forum.topic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.handler.IGetDetailModelByIdHandler;
import vn.hcmute.elearning.dtos.forum.UserForum;
import vn.hcmute.elearning.dtos.forum.topic.request.GetTopicDetailRequest;
import vn.hcmute.elearning.dtos.forum.topic.response.TopicInfo;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.entity.forum.ForumTopic;
import vn.hcmute.elearning.mapper.forum.ForumTopicMapper;
import vn.hcmute.elearning.repository.forum.ForumTopicRepository;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Getter
@Component
@RequiredArgsConstructor
public class GetTopicDetailHandler implements IGetDetailModelByIdHandler<Long, GetTopicDetailRequest, TopicInfo, ForumTopic, ForumTopicMapper> {

    private final ForumTopicRepository repository;
    private final ForumTopicMapper mapper;
    private final IUserService iUserService;

    @Override
    public void postMapping(ForumTopic entity, TopicInfo response) {
        User user = iUserService.getUserById(response.getCreatedBy());
        if (user != null) {
            response.setUser(new UserForum(user.getAvatar(), user.getFirstName(), user.getLastName()));
        }
    }
}
