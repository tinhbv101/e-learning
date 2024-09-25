package vn.hcmute.elearning.handler.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.auth.request.VerifyReActiveRequest;
import vn.hcmute.elearning.dtos.auth.response.VerifyReActiveResponse;
import vn.hcmute.elearning.service.interfaces.IAuthService;

@Component
@RequiredArgsConstructor
public class VerifyReActiveHandler extends RequestHandler<VerifyReActiveRequest, VerifyReActiveResponse> {
    private final IAuthService authService;

    @Override
    public VerifyReActiveResponse handle(VerifyReActiveRequest request) {
        return authService.verifyReActive(request);
    }
}
