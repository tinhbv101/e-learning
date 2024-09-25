package vn.hcmute.elearning.handler.address;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.address.request.GetWardsByDistrictV2Request;
import vn.hcmute.elearning.dtos.address.response.GetWardsByDistrictV2Response;
import vn.hcmute.elearning.entity.Ward;
import vn.hcmute.elearning.model.WardModel;
import vn.hcmute.elearning.service.AddressService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetWardsByDistrictV2Handler extends RequestHandler<GetWardsByDistrictV2Request, GetWardsByDistrictV2Response> {
    private final AddressService addressService;
    @Override
    public GetWardsByDistrictV2Response handle(GetWardsByDistrictV2Request request) {
        List<Ward> wards = addressService.getListWardsByDistrictId(request.getDistrictId());
        List<WardModel> wardModels = wards.parallelStream().map(WardModel::new).collect(Collectors.toList());
        return new GetWardsByDistrictV2Response(wardModels);
    }
}
