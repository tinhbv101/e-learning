package vn.hcmute.elearning.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UserInfo {
    private String username;

    private String fullName;

    private String phone;

    private String email;

    private boolean isVerifiedMail;

    private boolean isVerifiedPhone;
}
