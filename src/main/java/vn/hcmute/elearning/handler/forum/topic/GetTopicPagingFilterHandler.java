package vn.hcmute.elearning.handler.forum.topic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.PageResponseData;
import vn.hcmute.elearning.core.handler.IGetInfoListFilterPagingHandler;
import vn.hcmute.elearning.dtos.forum.UserForum;
import vn.hcmute.elearning.dtos.forum.topic.request.GetTopicPagingFilterRequest;
import vn.hcmute.elearning.dtos.forum.topic.response.TopicInfo;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.entity.forum.ForumTopic;
import vn.hcmute.elearning.mapper.forum.ForumTopicMapper;
import vn.hcmute.elearning.repository.forum.ForumTopicRepository;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Getter
@Component
@RequiredArgsConstructor
public class GetTopicPagingFilterHandler implements IGetInfoListFilterPagingHandler<Long, GetTopicPagingFilterRequest, TopicInfo, ForumTopic, ForumTopicMapper> {

    private final ForumTopicRepository repository;
    private final ForumTopicMapper mapper;
    private final IUserService iUserService;

    @Override
    public void postMapping(Page<ForumTopic> entityPage, PageResponseData<TopicInfo> responsePage) {
        responsePage.getData().forEach(topicInfo -> {
            User user = iUserService.getUserById(topicInfo.getCreatedBy());
            if (user != null) {
                topicInfo.setUser(new UserForum(user.getAvatar(), user.getFirstName(), user.getLastName()));
            }
        });
    }
}
