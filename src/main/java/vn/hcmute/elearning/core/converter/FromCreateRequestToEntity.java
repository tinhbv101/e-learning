package vn.hcmute.elearning.core.converter;

import vn.hcmute.elearning.core.BaseRequestData;

public interface FromCreateRequestToEntity<T extends BaseRequestData, E> {

    E fromCreateRequestToEntity(T request);
}
