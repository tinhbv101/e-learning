package vn.hcmute.elearning.dtos.teacher.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.model.teacher.TeacherDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetInfoTeacherResponse extends BaseResponseData {
    private TeacherDto teacher;
}
