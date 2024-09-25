package vn.hcmute.elearning.handler.forum.reaction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.handler.ICreateModelHandler;
import vn.hcmute.elearning.dtos.forum.reaction.request.CreateReactionRequest;
import vn.hcmute.elearning.entity.forum.ForumReaction;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.forum.ForumReactionMapper;
import vn.hcmute.elearning.repository.forum.ForumReactionRepository;

@Getter
@Component
@RequiredArgsConstructor
public class CreateReactionHandler implements ICreateModelHandler<ForumReaction.ReactionId, CreateReactionRequest, ForumReaction, ForumReactionMapper> {

    private final ForumReactionRepository repository;
    private final ForumReactionMapper mapper;

    @Override
    public CreateReactionRequest preHandle(CreateReactionRequest request) {
        if (repository.existsByPostIdAndUserIdAndReactionType(request.getPostId(), request.getUserId(), request.getReactionType())) {
            throw new InternalException(ResponseCode.FORUM_REACTION_EXISTED);
        }
        return request;
    }
}
