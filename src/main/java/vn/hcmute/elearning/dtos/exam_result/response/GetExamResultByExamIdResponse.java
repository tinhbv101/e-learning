package vn.hcmute.elearning.dtos.exam_result.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.utils.Paginate;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetExamResultByExamIdResponse extends BaseResponseData {
    private List<GetDetailExamResultResponse> examResults;
    private Paginate paginate;
}
