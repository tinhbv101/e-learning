package vn.hcmute.elearning.dtos.teacher.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseResponseData;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherGetBalanceResponse extends BaseResponseData {
    private long balance;
}
