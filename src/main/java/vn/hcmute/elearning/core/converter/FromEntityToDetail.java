package vn.hcmute.elearning.core.converter;

import vn.hcmute.elearning.core.BaseResponseData;

public interface FromEntityToDetail<T extends BaseResponseData, E> {

    T fromEntityToDetail(E entity);
}
