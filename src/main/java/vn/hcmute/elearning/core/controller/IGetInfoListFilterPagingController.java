package vn.hcmute.elearning.core.controller;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hcmute.elearning.core.BaseRequestData;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.core.PageResponseData;
import vn.hcmute.elearning.core.ResponseBase;

import javax.validation.Valid;

/**
 * @param <T> request model {@link BaseRequestData}
 * @param <I> response {@link BaseResponseData}
 */
@Validated
@RequestMapping("/")
public interface IGetInfoListFilterPagingController<T extends BaseRequestData, I> extends IBaseController {

    @Transactional(readOnly = true)
    @GetMapping("list/page")
    default ResponseEntity<ResponseBase<PageResponseData<I>>> getInfoListWithFilterPaging(@ParameterObject @Valid T request, @ParameterObject Pageable pageable) {
        request.setPageable(pageable);
        return execute(request);
    }
}
