package vn.hcmute.elearning.dtos.admin.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vn.hcmute.elearning.core.BaseRequestData;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class BanAdminRequest extends BaseRequestData {
    private String id;
}
