package vn.hcmute.elearning.handler.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.auth.request.ResetPasswordRequest;
import vn.hcmute.elearning.dtos.auth.response.ResetPasswordResponse;
import vn.hcmute.elearning.service.interfaces.IAuthService;

@Component
@RequiredArgsConstructor
public class ResetPasswordHandler extends RequestHandler<ResetPasswordRequest, ResetPasswordResponse> {
    private final IAuthService authService;

    @Override
    public ResetPasswordResponse handle(ResetPasswordRequest request) {
        return authService.resetPassword(request);
    }
}
