package vn.hcmute.elearning.dtos.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vn.hcmute.elearning.core.BaseResponseData;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordResponse extends BaseResponseData {
    private String message;
    private boolean success;
}
