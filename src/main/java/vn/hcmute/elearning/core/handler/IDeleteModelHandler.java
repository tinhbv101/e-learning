package vn.hcmute.elearning.core.handler;

import vn.hcmute.elearning.core.BaseID;
import vn.hcmute.elearning.core.BaseRequestData;
import vn.hcmute.elearning.core.Handler;
import vn.hcmute.elearning.dtos.StatusResponse;

public interface IDeleteModelHandler<ID, T extends BaseRequestData & BaseID<?>, E> extends
        IBaseHandler<ID, T, E>,
        Handler<T, StatusResponse> {

    @Override
    default StatusResponse handle(T request) {
        request = preHandle(request);
        E entity = getRepository().findById(request.convertedId())
                .orElseThrow(notFoundSupplier());
        validate(entity, request);
        getRepository().delete(entity);
        postHandle(entity, request);
        return new StatusResponse(true);
    }

    default void validate(E entity, T request) {

    }

    default void postHandle(E entity, T request) {

    }
}
