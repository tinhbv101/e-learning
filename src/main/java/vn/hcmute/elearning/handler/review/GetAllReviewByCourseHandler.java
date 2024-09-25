package vn.hcmute.elearning.handler.review;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.review.request.GetAllReviewByCourseRequest;
import vn.hcmute.elearning.dtos.review.response.GetAllReviewByCourseResponse;
import vn.hcmute.elearning.dtos.review.response.ReviewResponse;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.entity.Review;
import vn.hcmute.elearning.service.interfaces.ICourseService;
import vn.hcmute.elearning.service.interfaces.IReviewService;
import vn.hcmute.elearning.utils.Paginate;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetAllReviewByCourseHandler extends RequestHandler<GetAllReviewByCourseRequest, GetAllReviewByCourseResponse> {
    private final IReviewService reviewService;
    private final ICourseService courseService;
    @Override
    public GetAllReviewByCourseResponse handle(GetAllReviewByCourseRequest request) {
        Course course = courseService.getCourseByIdNotNull(request.getCourseId());
        Page<Review> page = reviewService.getAllByCourseId(request.getCourseId(), request.getPageable());
        List<ReviewResponse> list = page.getContent().stream()
            .map(review -> new ReviewResponse(review, request.getUserId()))
            .collect(Collectors.toList());
        return new GetAllReviewByCourseResponse(list, new Paginate(page));
    }
}
