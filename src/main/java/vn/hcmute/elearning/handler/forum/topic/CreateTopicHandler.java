package vn.hcmute.elearning.handler.forum.topic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.handler.ICreateModelHandler;
import vn.hcmute.elearning.dtos.forum.topic.request.CreateTopicRequest;
import vn.hcmute.elearning.entity.forum.ForumCategory;
import vn.hcmute.elearning.entity.forum.ForumPost;
import vn.hcmute.elearning.entity.forum.ForumTopic;
import vn.hcmute.elearning.mapper.forum.ForumTopicMapper;
import vn.hcmute.elearning.repository.forum.ForumTopicRepository;
import vn.hcmute.elearning.service.forum.ForumCategoryService;
import vn.hcmute.elearning.service.forum.ForumPostService;

@Getter
@Component
@RequiredArgsConstructor
public class CreateTopicHandler implements ICreateModelHandler<Long, CreateTopicRequest, ForumTopic, ForumTopicMapper> {

    private final ForumTopicRepository repository;
    private final ForumTopicMapper mapper;
    private final ForumCategoryService categoryService;
    private final ForumPostService postService;

    @Override
    public void postMapping(ForumTopic entity, CreateTopicRequest request) {
        ForumCategory forumCategory = categoryService.findByIdNonNull(request.getCategoryId());
        ForumPost firstPost = new ForumPost()
                .setContent(request.getFirstPostContent())
                .setTopic(entity)
                .setOrdinal(1);
        entity.setCategory(forumCategory)
                .addPost(firstPost);
    }
}
