package vn.hcmute.elearning.handler.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.review.request.CreateCommentRequest;
import vn.hcmute.elearning.dtos.review.response.CommentResponse;
import vn.hcmute.elearning.entity.Comment;
import vn.hcmute.elearning.entity.Review;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.ICommentService;
import vn.hcmute.elearning.service.interfaces.IReviewService;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Component
@RequiredArgsConstructor
public class CreateCommentHandler extends RequestHandler<CreateCommentRequest, CommentResponse> {
    private final IUserService userService;
    private final IReviewService reviewService;
    private final ICommentService commentService;
    @Override
    public CommentResponse handle(CreateCommentRequest request) {
        User user = userService.getUserById(request.getUserId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        Review review = reviewService.getReviewByIdNotNull(request.getReviewId());
        Comment comment = new Comment()
            .setComment(request.getComment())
            .setUser(user)
            .setCourse(review.getCourse())
            .setReview(review);
        comment = commentService.save(comment);
        return new CommentResponse(comment, user.getId());
    }
}
