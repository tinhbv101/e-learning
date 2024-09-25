package vn.hcmute.elearning.mapper.forum;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import vn.hcmute.elearning.core.converter.FromCreateRequestToEntity;
import vn.hcmute.elearning.core.converter.FromEntityPageToInfoPage;
import vn.hcmute.elearning.core.converter.FromUpdateRequestToEntity;
import vn.hcmute.elearning.dtos.forum.reaction.request.CreateReactionRequest;
import vn.hcmute.elearning.dtos.forum.reaction.request.UpdateReactionRequest;
import vn.hcmute.elearning.dtos.forum.reaction.response.ReactionInfo;
import vn.hcmute.elearning.entity.forum.ForumReaction;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ForumReactionMapper extends
        FromCreateRequestToEntity<CreateReactionRequest, ForumReaction>,
        FromUpdateRequestToEntity<UpdateReactionRequest, ForumReaction>,
        FromEntityPageToInfoPage<ReactionInfo, ForumReaction> {
}
