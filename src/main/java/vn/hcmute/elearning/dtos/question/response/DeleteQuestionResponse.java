package vn.hcmute.elearning.dtos.question.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vn.hcmute.elearning.core.BaseResponseData;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class DeleteQuestionResponse extends BaseResponseData {
    private boolean success;
}
