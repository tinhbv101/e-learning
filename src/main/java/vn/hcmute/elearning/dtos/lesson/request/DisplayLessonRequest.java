package vn.hcmute.elearning.dtos.lesson.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vn.hcmute.elearning.core.BaseRequestData;
import vn.hcmute.elearning.enums.DisplayStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class DisplayLessonRequest extends BaseRequestData {
    @NotBlank
    private String lessonId;
    @NotNull
    private DisplayStatus status;
}
