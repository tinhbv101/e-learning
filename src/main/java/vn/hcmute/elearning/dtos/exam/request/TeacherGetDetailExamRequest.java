package vn.hcmute.elearning.dtos.exam.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseRequestData;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherGetDetailExamRequest extends BaseRequestData {
    private String id;
}
