package vn.hcmute.elearning.core.handler;

import vn.hcmute.elearning.core.BaseRequestData;
import vn.hcmute.elearning.core.Handler;
import vn.hcmute.elearning.core.converter.FromCreateRequestToEntity;
import vn.hcmute.elearning.dtos.StatusResponse;

public interface ICreateModelHandler<ID, T extends BaseRequestData, E, M extends FromCreateRequestToEntity<T, E>> extends
        IBaseHandler<ID, T, E>,
        IBaseMapperHandler<M>,
        Handler<T, StatusResponse> {

    default StatusResponse handle(T request) {
        request = preHandle(request);
        E entity = getMapper().fromCreateRequestToEntity(request);
        postMapping(entity, request);
        entity = getRepository().save(entity);
        postHandle(entity, request);
        return new StatusResponse(true);
    }

    default void postMapping(E entity, T request) {

    }

    default void postHandle(E entity, T request) {

    }
}
