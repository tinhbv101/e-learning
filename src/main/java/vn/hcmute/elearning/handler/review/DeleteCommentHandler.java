package vn.hcmute.elearning.handler.review;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.review.request.DeleteCommentRequest;
import vn.hcmute.elearning.entity.Comment;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.ICommentService;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Component
@RequiredArgsConstructor
public class DeleteCommentHandler extends RequestHandler<DeleteCommentRequest, StatusResponse> {
    private final ICommentService commentService;
    private final IUserService userService;
    @Override
    public StatusResponse handle(DeleteCommentRequest request) {
        User user = userService.getUserByIdNotNull(request.getUserId());
        Comment comment = commentService.getByIdNotNull(request.getId());
        if (!StringUtils.equals(user.getId(), comment.getUser().getId())) {
            throw new InternalException(ResponseCode.USER_INVALID);
        }
        commentService.deleteById(request.getId());
        return new StatusResponse(true);
    }
}
