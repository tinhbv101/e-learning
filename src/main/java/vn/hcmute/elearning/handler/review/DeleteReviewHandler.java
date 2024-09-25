package vn.hcmute.elearning.handler.review;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.review.request.DeleteReviewRequest;
import vn.hcmute.elearning.dtos.review.response.DeleteReviewResponse;
import vn.hcmute.elearning.entity.Review;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.IReviewService;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Component
@RequiredArgsConstructor
public class DeleteReviewHandler extends RequestHandler<DeleteReviewRequest, DeleteReviewResponse> {
    private final IReviewService reviewService;
    private final IUserService userService;

    @Override
    public DeleteReviewResponse handle(DeleteReviewRequest request) {
        User user = userService.getUserById(request.getUserId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        Review review = reviewService.getReviewByIdNotNull(request.getId());
        if (!StringUtils.equals(user.getId(), review.getUser().getId())) {
            throw new InternalException(ResponseCode.USER_INVALID);
        }
        reviewService.deleteReview(request.getId());
        return new DeleteReviewResponse(true);
    }
}
