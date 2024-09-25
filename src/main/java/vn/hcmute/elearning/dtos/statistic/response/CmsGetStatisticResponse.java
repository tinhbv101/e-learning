package vn.hcmute.elearning.dtos.statistic.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.model.CmsStatisticModel;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CmsGetStatisticResponse extends BaseResponseData {
    private List<CmsStatisticModel> statistics;
}
