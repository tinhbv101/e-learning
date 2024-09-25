package vn.hcmute.elearning.mapper.forum;

import org.mapstruct.*;
import vn.hcmute.elearning.core.converter.*;
import vn.hcmute.elearning.dtos.forum.topic.request.CreateTopicRequest;
import vn.hcmute.elearning.dtos.forum.topic.request.UpdateTopicRequest;
import vn.hcmute.elearning.dtos.forum.topic.response.TopicInfo;
import vn.hcmute.elearning.entity.forum.ForumTopic;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ForumTopicMapper extends
        FromCreateRequestToEntity<CreateTopicRequest, ForumTopic>,
        FromUpdateRequestToEntity<UpdateTopicRequest, ForumTopic>,
        FromEntityPageToInfoPage<TopicInfo, ForumTopic>,
        FromEntityToInfo<TopicInfo, ForumTopic>, FromEntityToDetail<TopicInfo, ForumTopic> {

    @Override
    @Named("fromEntityToInfoMapper")
    @Mapping(source = "category.id", target = "categoryId")
    TopicInfo fromEntityToInfo(ForumTopic entity);

    @Override
    @Named("fromEntityToInfoFilterPageMapper")
    @Mapping(source = "category.id", target = "categoryId")
    TopicInfo fromEntityToInfoFilterPage(ForumTopic entity);
}
