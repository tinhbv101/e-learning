package vn.hcmute.elearning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.hcmute.elearning.entity.Review;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.repository.ReviewRepository;
import vn.hcmute.elearning.service.interfaces.IReviewService;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review getReviewById(String id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public Review getReviewByIdNotNull(String id) {
        return reviewRepository.findById(id)
            .orElseThrow(() -> new InternalException(ResponseCode.REVIEW_NOT_FOUND));
    }

    @Override
    public void deleteReview(String id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public Page<Review> getAllByCourseId(String courseId, Pageable pageable) {
        return reviewRepository.findAllByCourseId(courseId, pageable);
    }

    @Override
    public Review getReviewByUserIdAndCourseId(String userId, String courseId) {
        return reviewRepository.findByCourseIdAndUserId(courseId, userId).orElse(null);
    }
}
