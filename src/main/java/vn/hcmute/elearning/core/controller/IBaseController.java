package vn.hcmute.elearning.core.controller;

import org.springframework.http.ResponseEntity;
import vn.hcmute.elearning.core.BaseRequestData;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.core.ResponseBase;

public interface IBaseController {

    <T extends BaseRequestData, I extends BaseResponseData> ResponseEntity<ResponseBase<I>> execute(T request);
}
