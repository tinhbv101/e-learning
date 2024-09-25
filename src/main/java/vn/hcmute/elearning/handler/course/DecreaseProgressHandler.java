package vn.hcmute.elearning.handler.course;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.course.request.DecreaseProgressRequest;
import vn.hcmute.elearning.service.interfaces.IProgressService;

@Component
@RequiredArgsConstructor
public class DecreaseProgressHandler extends RequestHandler<DecreaseProgressRequest, StatusResponse> {
    private final IProgressService progressService;
    @Override
    public StatusResponse handle(DecreaseProgressRequest request) {
        progressService.decreaseProgress(request);
        return new StatusResponse(true);
    }
}
