package vn.hcmute.elearning.dtos.lesson.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseRequestData;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteLessonRequest extends BaseRequestData {
    private String id;
}
