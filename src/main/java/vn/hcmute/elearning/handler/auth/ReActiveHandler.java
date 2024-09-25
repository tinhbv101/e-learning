package vn.hcmute.elearning.handler.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.auth.request.ReActiveRequest;
import vn.hcmute.elearning.dtos.auth.response.ReActiveResponse;
import vn.hcmute.elearning.service.interfaces.IAuthService;

@Component
@RequiredArgsConstructor
public class ReActiveHandler extends RequestHandler<ReActiveRequest, ReActiveResponse> {
    private final IAuthService authService;

    @Override
    public ReActiveResponse handle(ReActiveRequest request) {
        return authService.reActive(request);
    }
}
