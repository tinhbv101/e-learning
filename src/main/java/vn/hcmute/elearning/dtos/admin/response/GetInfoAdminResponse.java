package vn.hcmute.elearning.dtos.admin.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.model.admin.AdministratorDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetInfoAdminResponse extends BaseResponseData {
    private AdministratorDto administrator;
}
