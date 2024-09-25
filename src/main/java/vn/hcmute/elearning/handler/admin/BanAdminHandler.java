package vn.hcmute.elearning.handler.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.admin.request.BanAdminRequest;
import vn.hcmute.elearning.entity.Administrator;
import vn.hcmute.elearning.enums.AdminStatus;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.KeycloakService;
import vn.hcmute.elearning.service.RedisService;
import vn.hcmute.elearning.service.interfaces.IAdministratorService;

@Component
@RequiredArgsConstructor
public class BanAdminHandler extends RequestHandler<BanAdminRequest, StatusResponse> {
    private final IAdministratorService administratorService;
    private final RedisService redisService;
    private final KeycloakService keycloakService;

    @Override
    public StatusResponse handle(BanAdminRequest request) {
        Administrator administrator = administratorService.getById(request.getId());
        if (administrator == null) {
            throw new InternalException(ResponseCode.ADMIN_IS_NOT_EXISTED);
        }
        if (administrator.getId().equals(request.getUserId())) {
            throw new InternalException(ResponseCode.ADMIN_NOT_INACTIVE_YOURSELF);
        }
        administrator.setStatus(AdminStatus.INACTIVE);
        administratorService.save(administrator);

        keycloakService.revokeAllSessionUser(administrator.getId());
        return new StatusResponse();
    }
}
