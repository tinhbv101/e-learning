package vn.hcmute.elearning.handler.review;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.review.request.UpdateReviewRequest;
import vn.hcmute.elearning.dtos.review.response.ReviewResponse;
import vn.hcmute.elearning.entity.Review;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.IReviewService;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Component
@RequiredArgsConstructor
public class UpdateReviewHandler extends RequestHandler<UpdateReviewRequest, ReviewResponse> {
    private final IReviewService reviewService;
    private final IUserService userService;

    @Override
    public ReviewResponse handle(UpdateReviewRequest request) {
        User user = userService.getUserById(request.getUserId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        Review review = reviewService.getReviewByIdNotNull(request.getId());
        if (!StringUtils.equals(user.getId(), review.getUser().getId())) {
            throw new InternalException(ResponseCode.USER_INVALID);
        }
        review.setSubject(request.getSubject());
        review.setContent(request.getContent());
        review.setStar(request.getStar());
        return new ReviewResponse(reviewService.save(review), user.getId());
    }
}
