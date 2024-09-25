package vn.hcmute.elearning.dtos.auth.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vn.hcmute.elearning.core.BaseRequestData;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequest extends BaseRequestData {
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String token;
    private String password;
}
