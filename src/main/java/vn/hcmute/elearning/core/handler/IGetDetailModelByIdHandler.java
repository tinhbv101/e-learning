package vn.hcmute.elearning.core.handler;

import vn.hcmute.elearning.core.BaseID;
import vn.hcmute.elearning.core.BaseRequestData;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.core.Handler;
import vn.hcmute.elearning.core.converter.FromEntityToDetail;

public interface IGetDetailModelByIdHandler<ID, T extends BaseRequestData & BaseID<?>, U extends BaseResponseData, E, M extends FromEntityToDetail<U, E>> extends
        IBaseHandler<ID, T, E>,
        IBaseMapperHandler<M>,
        Handler<T, U> {

    @Override
    default U handle(T request) {
        request = preHandle(request);
        E entity = getRepository().findById(request.convertedId())
                .orElseThrow(notFoundSupplier());
        U response = getMapper().fromEntityToDetail(entity);
        postMapping(entity, response);
        postHandle(entity, request);
        return response;
    }

    default void postMapping(E entity, U response) {

    }

    default void postHandle(E entity, T request) {

    }
}
