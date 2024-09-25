package vn.hcmute.elearning.dtos.forum.reaction.request;

import lombok.Getter;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseRequestId;
import vn.hcmute.elearning.entity.forum.ForumReaction;
import vn.hcmute.elearning.enums.forum.ForumReactionType;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateReactionRequest extends BaseRequestId<String> {

    @NotNull
    private ForumReactionType reactionType;

    @Override
    public <T> T convertedId() {
        return (T) new ForumReaction.ReactionId(getUserId(), getId());
    }
}
