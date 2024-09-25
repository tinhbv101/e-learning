package vn.hcmute.elearning.client.paygate.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.hcmute.elearning.core.BaseResponseData;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBeneficiaryResponse extends BaseResponseData {

    private String accountName;

}
