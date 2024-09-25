package vn.hcmute.elearning.handler.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.review.request.CreateReviewRequest;
import vn.hcmute.elearning.dtos.review.response.ReviewResponse;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.entity.Review;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.ICourseService;
import vn.hcmute.elearning.service.interfaces.IReviewService;
import vn.hcmute.elearning.service.interfaces.IUserService;

import java.util.ArrayList;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CreateReviewHandler extends RequestHandler<CreateReviewRequest, ReviewResponse> {
    private final IUserService userService;
    private final ICourseService courseService;
    private final IReviewService reviewService;

    @Override
    public ReviewResponse handle(CreateReviewRequest request) {
        User user = userService.getUserById(request.getUserId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        Course course = courseService.getCourseById(request.getCourseId());
        if (course == null) {
            throw new InternalException(ResponseCode.COURSE_NOT_FOUND);
        }
        Set<User> courseUsers = course.getUsers();
        if (courseUsers == null || !courseUsers.contains(user)) {
            throw new InternalException(ResponseCode.USER_INVALID);
        }
        if (reviewService.getReviewByUserIdAndCourseId(user.getId(), course.getId()) != null) {
            throw new InternalException(ResponseCode.REVIEW_EXISTED);
        }
        Review review = new Review();
        review.setUser(user);
        review.setCourse(course);
        review.setSubject(request.getSubject());
        review.setContent(request.getContent());
        review.setStar(request.getStar());
        review = reviewService.save(review);
        return new ReviewResponse(review, user.getId());
    }
}
