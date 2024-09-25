package vn.hcmute.elearning.handler.forum.reaction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.handler.IDeleteModelHandler;
import vn.hcmute.elearning.dtos.forum.reaction.request.DeleteReactionRequest;
import vn.hcmute.elearning.entity.forum.ForumReaction;
import vn.hcmute.elearning.repository.forum.ForumReactionRepository;

@Getter
@Component
@RequiredArgsConstructor
public class DeleteReactionHandler implements IDeleteModelHandler<ForumReaction.ReactionId, DeleteReactionRequest, ForumReaction> {

    private final ForumReactionRepository repository;
}
