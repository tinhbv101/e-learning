package vn.hcmute.elearning.handler.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.auth.request.ForgotPasswordRequest;
import vn.hcmute.elearning.dtos.auth.response.ForgotPasswordResponse;
import vn.hcmute.elearning.service.interfaces.IAuthService;

@Component
@RequiredArgsConstructor
public class ForgotPasswordHandler extends RequestHandler<ForgotPasswordRequest, ForgotPasswordResponse> {
    private final IAuthService authService;

    @Override
    public ForgotPasswordResponse handle(ForgotPasswordRequest request) {
        return authService.forgotPassword(request);
    }
}
