package vn.hcmute.elearning.dtos.admin.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import vn.hcmute.elearning.common.Constant;
import vn.hcmute.elearning.core.BaseRequestData;
import vn.hcmute.elearning.enums.AdminStatus;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class GetListAdminRequest extends BaseRequestData {
    private String fullName;

    private AdminStatus status;

    @DateTimeFormat(pattern = Constant.dd_MM_yyyy)
    private LocalDate fromDate;

    @DateTimeFormat(pattern = Constant.dd_MM_yyyy)
    private LocalDate toDate;
}
