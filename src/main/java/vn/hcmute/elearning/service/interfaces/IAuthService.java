package vn.hcmute.elearning.service.interfaces;

import org.keycloak.common.VerificationException;
import vn.hcmute.elearning.dtos.auth.request.*;
import vn.hcmute.elearning.dtos.auth.response.*;

public interface IAuthService {
    AccessTokenResponseCustom login(LoginRequest request) throws VerificationException;

    RegisterResponse register(RegisterRequest request);

    ReActiveResponse reActive(ReActiveRequest request);

    VerifyReActiveResponse verifyReActive(VerifyReActiveRequest request);

    AccessTokenResponseCustom refreshToken(RefreshTokenRequest request);

    VerifyEmailResponse verifyEmail(VerifyEmailRequest request);

    ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request);

    ResetPasswordResponse resetPassword(ResetPasswordRequest request);
}
