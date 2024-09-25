package vn.hcmute.elearning.dtos.address.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.model.WardDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetWardsbyDistrictResponse extends BaseResponseData {
    private List<WardDto> wards;
}
