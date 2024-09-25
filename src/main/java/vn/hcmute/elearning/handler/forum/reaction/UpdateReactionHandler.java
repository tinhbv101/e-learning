package vn.hcmute.elearning.handler.forum.reaction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.handler.IUpdateModelHandler;
import vn.hcmute.elearning.dtos.forum.reaction.request.UpdateReactionRequest;
import vn.hcmute.elearning.entity.forum.ForumReaction;
import vn.hcmute.elearning.mapper.forum.ForumReactionMapper;
import vn.hcmute.elearning.repository.forum.ForumReactionRepository;

@Getter
@Component
@RequiredArgsConstructor
public class UpdateReactionHandler implements IUpdateModelHandler<ForumReaction.ReactionId, UpdateReactionRequest, ForumReaction, ForumReactionMapper> {

    private final ForumReactionRepository repository;
    private final ForumReactionMapper mapper;
}
