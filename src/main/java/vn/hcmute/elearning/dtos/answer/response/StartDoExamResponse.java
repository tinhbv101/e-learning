package vn.hcmute.elearning.dtos.answer.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseResponseData;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StartDoExamResponse extends BaseResponseData {
    private String code;
}
