package vn.hcmute.elearning.dtos.lesson.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.entity.Lesson;
import vn.hcmute.elearning.enums.DisplayStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class LessonResponse extends BaseResponseData {
    private String id;
    private String name;
    private String description;
    private DisplayStatus displayStatus;
    private LocalDateTime createAt;

    public LessonResponse(Lesson lesson) {
        this.id = lesson.getId();
        this.name = lesson.getName();
        this.description = lesson.getDescription();
        this.displayStatus = lesson.getDisplayStatus();
        this.createAt = lesson.getCreateDate();
    }
}
