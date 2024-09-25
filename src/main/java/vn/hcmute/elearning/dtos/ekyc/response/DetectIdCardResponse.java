package vn.hcmute.elearning.dtos.ekyc.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.model.EkycDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetectIdCardResponse extends BaseResponseData {
    private EkycDTO idCard;
}
