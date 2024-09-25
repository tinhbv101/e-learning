package vn.hcmute.elearning.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hcmute.elearning.core.BaseID;
import vn.hcmute.elearning.core.BaseRequestData;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.core.ResponseBase;

import javax.validation.Valid;

/**
 * @param <T> request model {@link BaseRequestData}
 * @param <I> response {@link BaseResponseData}
 */
@Validated
@RequestMapping("/")
public interface IGetDetailByIdController<T extends BaseRequestData & BaseID<?>, I extends BaseResponseData> extends IBaseController {

    @Transactional(readOnly = true)
    @GetMapping("{id}/detail")
    default ResponseEntity<ResponseBase<I>> getDetailById(@Valid T id) {
        return execute(id);
    }
}
