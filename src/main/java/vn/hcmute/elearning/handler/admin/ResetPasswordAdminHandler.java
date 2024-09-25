package vn.hcmute.elearning.handler.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.admin.request.ResetPasswordAdminRequest;
import vn.hcmute.elearning.entity.Administrator;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.KeycloakService;
import vn.hcmute.elearning.service.interfaces.IAdministratorService;

@Component
@RequiredArgsConstructor
public class ResetPasswordAdminHandler extends RequestHandler<ResetPasswordAdminRequest, StatusResponse> {
    private final IAdministratorService administratorService;
    private final KeycloakService keycloakService;

    @Override
    public StatusResponse handle(ResetPasswordAdminRequest request) {
        Administrator administrator = administratorService.getById(request.getId());
        if (administrator == null) {
            throw new InternalException(ResponseCode.ADMIN_IS_NOT_EXISTED);
        }
        boolean success = keycloakService.setUserPassword(administrator.getId(), request.getPassword());
        if (!success) {
            throw new InternalException(ResponseCode.FAILED);
        }
        return new StatusResponse();
    }
}
