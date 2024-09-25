package vn.hcmute.elearning.handler.address;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.address.request.GetDistrictsByProvinceV2Request;
import vn.hcmute.elearning.dtos.address.response.GetDistrictsByProvinceV2Response;
import vn.hcmute.elearning.entity.District;
import vn.hcmute.elearning.model.DistrictModel;
import vn.hcmute.elearning.service.AddressService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetDistrictsByProvinceV2Handler extends RequestHandler<GetDistrictsByProvinceV2Request, GetDistrictsByProvinceV2Response> {
    private final AddressService addressService;

    @Override
    public GetDistrictsByProvinceV2Response handle(GetDistrictsByProvinceV2Request request) {
        List<District> districts = addressService.getListDistrictsByProvinceId(request.getProvinceId());
        List<DistrictModel> districtModels = districts.parallelStream().map(DistrictModel::new).collect(Collectors.toList());
        return new GetDistrictsByProvinceV2Response(districtModels);
    }
}
