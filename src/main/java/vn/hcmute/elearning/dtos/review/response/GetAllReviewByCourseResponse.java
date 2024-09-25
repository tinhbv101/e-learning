package vn.hcmute.elearning.dtos.review.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.utils.Paginate;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetAllReviewByCourseResponse extends BaseResponseData {
    private List<ReviewResponse> reviews;
    private Paginate paginate;
}
