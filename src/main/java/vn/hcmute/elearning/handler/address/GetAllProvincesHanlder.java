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
import vn.hcmute.elearning.dtos.address.request.GetAllProvincesRequest;
import vn.hcmute.elearning.dtos.address.response.GetAllProvincesResponse;
import vn.hcmute.elearning.model.ProvinceDto;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllProvincesHanlder extends RequestHandler<GetAllProvincesRequest, GetAllProvincesResponse> {

    private final IRestClient restClient;
    @Value("${address-client.get-province-url}")
    private String host;

    @Override
    public GetAllProvincesResponse handle(GetAllProvincesRequest request) {
        String url = host;
        ResponseEntity<Object> response = restClient.callAPI(url, HttpMethod.GET, new HttpHeaders(), null);
        List<ProvinceDto> divisionDtos = (List<ProvinceDto>) response.getBody();
        return new GetAllProvincesResponse(divisionDtos);
    }
}
