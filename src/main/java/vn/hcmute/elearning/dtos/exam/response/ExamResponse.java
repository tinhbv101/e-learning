package vn.hcmute.elearning.dtos.exam.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.entity.Exam;
import vn.hcmute.elearning.enums.ExamStatus;
import vn.hcmute.elearning.enums.ExamType;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ExamResponse extends BaseResponseData {
    private String id;

    private LocalDateTime createAt;

    private String examName;

    private int timeMinute;

    private ExamType examType;

    private ExamStatus status;

    private int testAttempts;

    private int totalAttemptsCompleted;

    private Double examMaxScore;

    private Double highestScore;
    private boolean done = false;

    public ExamResponse(Exam exam) {
        this.id = exam.getId();
        this.examName = exam.getExamName();
        this.timeMinute = exam.getTimeMinute();
        this.examType = exam.getExamType();
        this.status = exam.getStatus();
        this.testAttempts = exam.getTestAttempts();
        this.createAt =exam.getCreateDate();

    }
}
