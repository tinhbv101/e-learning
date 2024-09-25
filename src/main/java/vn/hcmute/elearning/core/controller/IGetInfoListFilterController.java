package vn.hcmute.elearning.core.controller;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hcmute.elearning.core.BaseRequestData;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.core.BaseResponseList;
import vn.hcmute.elearning.core.ResponseBase;

import javax.validation.Valid;

/**
 * @param <T> request model {@link BaseRequestData}
 * @param <I> response {@link BaseResponseData}
 */
@Validated
@RequestMapping("/")
public interface IGetInfoListFilterController<T extends BaseRequestData, I> extends IBaseController {

    @Transactional(readOnly = true)
    @GetMapping("list")
    default ResponseEntity<ResponseBase<BaseResponseList<I>>> getInfoListWithFilter(@ParameterObject @Valid T request) {
        return execute(request);
    }
}
