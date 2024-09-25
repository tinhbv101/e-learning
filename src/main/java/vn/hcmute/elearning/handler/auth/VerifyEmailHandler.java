package vn.hcmute.elearning.handler.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.auth.request.VerifyEmailRequest;
import vn.hcmute.elearning.dtos.auth.response.VerifyEmailResponse;
import vn.hcmute.elearning.service.interfaces.IAuthService;

@Component
@RequiredArgsConstructor
public class VerifyEmailHandler extends RequestHandler<VerifyEmailRequest, VerifyEmailResponse> {
    private final IAuthService authService;

    @Override
    public VerifyEmailResponse handle(VerifyEmailRequest request) {
        return authService.verifyEmail(request);
    }
}
