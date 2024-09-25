package vn.hcmute.elearning.handler.address;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.address.request.GetAllProvinceV2Request;
import vn.hcmute.elearning.dtos.address.response.GetAllProvinceV2Response;
import vn.hcmute.elearning.entity.Province;
import vn.hcmute.elearning.model.ProvinceModel;
import vn.hcmute.elearning.service.AddressService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetAllProvincesV2Hanlder extends RequestHandler<GetAllProvinceV2Request, GetAllProvinceV2Response> {

    private final AddressService addressService;

    @Override
    public GetAllProvinceV2Response handle(GetAllProvinceV2Request request) {
        List<Province> provinces = addressService.getListProvinces();
        List<ProvinceModel> provinceModels = provinces.parallelStream().map(ProvinceModel::new).collect(Collectors.toList());
        return new GetAllProvinceV2Response(provinceModels);
    }
}
