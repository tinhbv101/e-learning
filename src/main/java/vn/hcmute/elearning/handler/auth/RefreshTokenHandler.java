package vn.hcmute.elearning.handler.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.auth.request.RefreshTokenRequest;
import vn.hcmute.elearning.dtos.auth.response.AccessTokenResponseCustom;
import vn.hcmute.elearning.service.interfaces.IAuthService;

@Component
@RequiredArgsConstructor
public class RefreshTokenHandler extends RequestHandler<RefreshTokenRequest, AccessTokenResponseCustom> {
    private final IAuthService authService;

    @Override
    public AccessTokenResponseCustom handle(RefreshTokenRequest request) {
        return authService.refreshToken(request);
    }
}
