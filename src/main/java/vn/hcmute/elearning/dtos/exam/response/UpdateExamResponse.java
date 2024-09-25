package vn.hcmute.elearning.dtos.exam.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.entity.Exam;
import vn.hcmute.elearning.entity.Question;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class UpdateExamResponse extends BaseResponseData {
    private String id;
    private String lessonId;
    private String examName;
    private LocalDateTime createAt;
    private Integer questionTotal;
    private Integer timeMinute;
    private List<Question> questions;

    public UpdateExamResponse(Exam exam) {
        this.id = exam.getId();
        this.lessonId = exam.getLesson().getId();
        this.createAt = exam.getCreateDate();
        this.examName = exam.getExamName();
        this.timeMinute = exam.getTimeMinute();
        this.questions = exam.getQuestions();
    }
}
