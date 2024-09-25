package vn.hcmute.elearning.dtos.auth.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.hcmute.elearning.core.BaseRequestData;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginRequest extends BaseRequestData {
    private String username;

    private String password;
}
