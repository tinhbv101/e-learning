package vn.hcmute.elearning.handler.forum.reaction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.PageResponseData;
import vn.hcmute.elearning.core.handler.IGetInfoListFilterPagingHandler;
import vn.hcmute.elearning.dtos.forum.reaction.request.GetReactionFilterPagingRequest;
import vn.hcmute.elearning.dtos.forum.reaction.response.ReactionInfo;
import vn.hcmute.elearning.entity.forum.ForumReaction;
import vn.hcmute.elearning.mapper.forum.ForumReactionMapper;
import vn.hcmute.elearning.repository.forum.ForumReactionRepository;

@Getter
@Component
@RequiredArgsConstructor
public class GetReactionFilterPagingHandler implements IGetInfoListFilterPagingHandler<ForumReaction.ReactionId, GetReactionFilterPagingRequest, ReactionInfo, ForumReaction, ForumReactionMapper> {

    private final ForumReactionRepository repository;
    private final ForumReactionMapper mapper;

    @Override
    public PageResponseData<ReactionInfo> handle(GetReactionFilterPagingRequest request) {
        return new PageResponseData<>(repository.findReactionFilterPaging(request, request.getPageable()));
    }
}
