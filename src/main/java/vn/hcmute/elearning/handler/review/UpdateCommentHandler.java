package vn.hcmute.elearning.handler.review;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.review.request.UpdateCommentRequest;
import vn.hcmute.elearning.dtos.review.response.CommentResponse;
import vn.hcmute.elearning.entity.Comment;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.ICommentService;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Component
@RequiredArgsConstructor
public class UpdateCommentHandler extends RequestHandler<UpdateCommentRequest, CommentResponse> {

    private final ICommentService commentService;
    private final IUserService userService;

    @Override
    public CommentResponse handle(UpdateCommentRequest request) {
        User user = userService.getUserByIdNotNull(request.getUserId());
        Comment comment = commentService.getByIdNotNull(request.getId());
        if (!StringUtils.equals(user.getId(), comment.getUser().getId())) {
            throw new InternalException(ResponseCode.USER_INVALID);
        }
        comment.setComment(request.getComment());
        comment = commentService.save(comment);
        return new CommentResponse(comment, user.getId());
    }
}
