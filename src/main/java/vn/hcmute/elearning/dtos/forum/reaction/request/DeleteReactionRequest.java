package vn.hcmute.elearning.dtos.forum.reaction.request;

import lombok.Getter;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseRequestId;
import vn.hcmute.elearning.entity.forum.ForumReaction;

@Getter
@Setter
public class DeleteReactionRequest extends BaseRequestId<String> {

    @Override
    public <T> T convertedId() {
        return (T) new ForumReaction.ReactionId(getUserId(), getId());
    }
}
