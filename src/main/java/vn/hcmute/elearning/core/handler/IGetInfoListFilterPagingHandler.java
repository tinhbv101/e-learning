package vn.hcmute.elearning.core.handler;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import vn.hcmute.elearning.core.BaseRequestData;
import vn.hcmute.elearning.core.Handler;
import vn.hcmute.elearning.core.PageResponseData;
import vn.hcmute.elearning.core.converter.FromEntityPageToInfoPage;

@SuppressWarnings("unchecked")
public interface IGetInfoListFilterPagingHandler<ID, T extends BaseRequestData, U, E, M extends FromEntityPageToInfoPage<U, E>> extends
        IBaseFilterHandler<ID, T, E>,
        IBaseMapperHandler<M>,
        Handler<T, PageResponseData<U>> {

    @Override
    default PageResponseData<U> handle(T request) {
        request = preHandle(request);
        Page<E> entities;
        if (request instanceof Specification) {
            entities = getSpecificationExecutor().findAll((Specification<E>) request, request.getPageable());
        } else {
            entities = getRepository().findAll(request.getPageable());
        }
        PageResponseData<U> response = getMapper().fromEntityPageToInfoFilterPage(entities);
        postMapping(entities, response);
        postHandle(entities, request);
        return response;
    }

    default void postMapping(Page<E> entityPage, PageResponseData<U> responsePage) {

    }

    default void postHandle(Page<E> entity, T request) throws UnsupportedOperationException {

    }
}
