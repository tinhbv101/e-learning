package vn.hcmute.elearning.core;

public abstract class RequestHandler<T extends BaseRequestData, I extends BaseResponseData> implements Handler<T, I> {
    public RequestHandler() {
    }
}
