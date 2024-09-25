package vn.hcmute.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.hcmute.elearning.controller.interfaces.IEkycController;
import vn.hcmute.elearning.core.BaseController;
import vn.hcmute.elearning.core.ResponseBase;
import vn.hcmute.elearning.dtos.ekyc.request.DetectIdCardRequest;
import vn.hcmute.elearning.dtos.ekyc.request.UpdateEkycRequest;
import vn.hcmute.elearning.dtos.ekyc.response.DetectIdCardResponse;
import vn.hcmute.elearning.dtos.ekyc.response.UpdateEkycResponse;

@RestController
public class EkycController extends BaseController implements IEkycController {
    @Override
    public ResponseEntity<ResponseBase<DetectIdCardResponse>> detectIdCard(DetectIdCardRequest request) {
        return this.execute(request, DetectIdCardResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<UpdateEkycResponse>> updateEkyc(UpdateEkycRequest request) {
        return this.execute(request);
    }
}
