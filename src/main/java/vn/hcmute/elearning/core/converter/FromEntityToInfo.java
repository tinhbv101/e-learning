package vn.hcmute.elearning.core.converter;

import org.mapstruct.Named;
import vn.hcmute.elearning.core.BaseResponseData;

public interface FromEntityToInfo<T extends BaseResponseData, E> {

    @Named("fromEntityToInfoMapper")
    T fromEntityToInfo(E entity);
}
