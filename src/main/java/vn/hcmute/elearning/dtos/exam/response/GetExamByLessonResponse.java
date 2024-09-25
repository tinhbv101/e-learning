package vn.hcmute.elearning.dtos.exam.response;

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
public class GetExamByLessonResponse extends BaseResponseData {
    private List<ExamResponse> exams;
    private Paginate paginate;
}
