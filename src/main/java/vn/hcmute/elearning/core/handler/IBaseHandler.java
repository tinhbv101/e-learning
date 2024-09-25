package vn.hcmute.elearning.core.handler;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hcmute.elearning.core.BaseRequestData;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;

import java.util.function.Supplier;

public interface IBaseHandler<ID, T extends BaseRequestData, E> {

    JpaRepository<E, ID> getRepository();

    default T preHandle(T request) {
        return request;
    }

    default InternalException notFound() {
        return new InternalException(ResponseCode.NOT_FOUND);
    }

    default Supplier<InternalException> notFoundSupplier() {
        return this::notFound;
    }

}
