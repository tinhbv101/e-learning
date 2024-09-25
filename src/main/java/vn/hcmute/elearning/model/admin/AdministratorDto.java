package vn.hcmute.elearning.model.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.enums.AdminStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdministratorDto {
    private String id;
    private String fullName;
    private String phone;
    private String email;
    private AdminStatus status;
    private LocalDateTime createDate;
}
