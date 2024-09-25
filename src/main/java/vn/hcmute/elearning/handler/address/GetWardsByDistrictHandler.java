package vn.hcmute.elearning.handler.address;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.client.IRestClient;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.address.request.GetWardsByDistrictRequest;
import vn.hcmute.elearning.dtos.address.response.GetWardsbyDistrictResponse;
import vn.hcmute.elearning.model.DistrictDto;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetWardsByDistrictHandler extends RequestHandler<GetWardsByDistrictRequest, GetWardsbyDistrictResponse> {

    private final IRestClient restClient;

    @Value("${address-client.get-wards-url}")
    private String host;
    @Override
    public GetWardsbyDistrictResponse handle(GetWardsByDistrictRequest request) {
        String url = String.format(host, request.getDistrictCode());
        ResponseEntity<DistrictDto> response = restClient.callAPI(url, HttpMethod.GET, new HttpHeaders(), null, DistrictDto.class);
        return new GetWardsbyDistrictResponse(Objects.requireNonNull(response.getBody()).getWards());
    }
}
