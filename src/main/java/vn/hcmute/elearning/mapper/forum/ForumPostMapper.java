package vn.hcmute.elearning.mapper.forum;

import org.mapstruct.*;
import vn.hcmute.elearning.core.converter.FromCreateRequestToEntity;
import vn.hcmute.elearning.core.converter.FromEntityPageToInfoPage;
import vn.hcmute.elearning.core.converter.FromUpdateRequestToEntity;
import vn.hcmute.elearning.dtos.forum.post.request.CreatePostRequest;
import vn.hcmute.elearning.dtos.forum.post.request.UpdatePostRequest;
import vn.hcmute.elearning.dtos.forum.post.response.PostInfo;
import vn.hcmute.elearning.entity.forum.ForumPost;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ForumPostMapper extends
        FromCreateRequestToEntity<CreatePostRequest, ForumPost>,
        FromUpdateRequestToEntity<UpdatePostRequest, ForumPost>,
        FromEntityPageToInfoPage<PostInfo, ForumPost> {

    @Override
    @Named("fromEntityToInfoFilterPageMapper")
    @Mapping(source = "topic.id", target = "topicId")
    PostInfo fromEntityToInfoFilterPage(ForumPost entity);
}
