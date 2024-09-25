package vn.hcmute.elearning.handler.forum.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.BaseResponseList;
import vn.hcmute.elearning.core.handler.IGetInfoListFilterHandler;
import vn.hcmute.elearning.dtos.forum.category.request.GetCategoryListRequest;
import vn.hcmute.elearning.dtos.forum.category.response.CategoryInfo;
import vn.hcmute.elearning.entity.forum.ForumCategory;
import vn.hcmute.elearning.entity.forum.ForumTopic;
import vn.hcmute.elearning.mapper.forum.ForumCategoryMapper;
import vn.hcmute.elearning.mapper.forum.ForumTopicMapper;
import vn.hcmute.elearning.repository.forum.ForumCategoryRepository;
import vn.hcmute.elearning.service.forum.ForumTopicService;

import java.util.List;

@Getter
@Component
@RequiredArgsConstructor
public class GetCategoryListFilterHandler implements IGetInfoListFilterHandler<String, GetCategoryListRequest, CategoryInfo, ForumCategory, ForumCategoryMapper> {

    private final ForumCategoryRepository repository;
    private final ForumCategoryMapper mapper;
    private final ForumTopicService topicService;
    private final ForumTopicMapper forumTopicMapper;


    @Override
    public BaseResponseList<CategoryInfo> handle(GetCategoryListRequest request) {
        List<CategoryInfo> categoryInfoList = repository.findAllList();
        for (CategoryInfo category : categoryInfoList) {
            ForumTopic latestTopic = topicService.getLatestPostByCategoryId(category.getId());
            category.setLatestTopicInfo(forumTopicMapper.fromEntityToInfo(latestTopic));
        }
        return new BaseResponseList<>(categoryInfoList);
    }
}
