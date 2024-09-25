package vn.hcmute.elearning.handler.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.TokenVerifier;
import org.keycloak.representations.RefreshToken;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.auth.request.LogoutRequest;
import vn.hcmute.elearning.dtos.auth.response.LogoutResponse;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.KeycloakService;
import vn.hcmute.elearning.service.RedisService;

@Component
@RequiredArgsConstructor
@Slf4j
public class LogoutHandler extends RequestHandler<LogoutRequest, LogoutResponse> {
    private final KeycloakService keycloakService;
    private final RedisService redisService;

    @Override
    public LogoutResponse handle(LogoutRequest request) {
        try {
            RefreshToken refreshToken = TokenVerifier.create(request.getRefreshToken(), RefreshToken.class).getToken();
            if (keycloakService.invalidateToken(request.getRefreshToken())) {
                redisService.deleteKey(refreshToken.getSessionState());
                return new LogoutResponse();
            }
        } catch (Exception e) {
            log.error("logout error: {}", e.getLocalizedMessage());
        }
        throw new InternalException(ResponseCode.REFRESH_TOKEN_INVALID);
    }
}
