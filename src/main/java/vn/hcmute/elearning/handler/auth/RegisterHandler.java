package vn.hcmute.elearning.handler.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.auth.request.RegisterRequest;
import vn.hcmute.elearning.dtos.auth.response.RegisterResponse;
import vn.hcmute.elearning.service.AuthService;

@Component
@RequiredArgsConstructor
public class RegisterHandler extends RequestHandler<RegisterRequest, RegisterResponse> {
    private final AuthService authService;

    @Override
    public RegisterResponse handle(RegisterRequest request) {
        return authService.register(request);
    }
}
