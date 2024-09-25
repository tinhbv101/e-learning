package vn.hcmute.elearning.handler.auth;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.auth.request.LoginRequest;
import vn.hcmute.elearning.dtos.auth.response.AccessTokenResponseCustom;
import vn.hcmute.elearning.service.interfaces.IAuthService;

@Component
@RequiredArgsConstructor
public class LoginHandler extends RequestHandler<LoginRequest, AccessTokenResponseCustom> {
    private final IAuthService authService;

    @SneakyThrows
    @Override
    public AccessTokenResponseCustom handle(LoginRequest request) {
        return authService.login(request);
    }
}
