package vn.hcmute.elearning.handler.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.admin.request.UnbanAdminRequest;
import vn.hcmute.elearning.entity.Administrator;
import vn.hcmute.elearning.enums.AdminStatus;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.IAdministratorService;

@Component
@RequiredArgsConstructor
public class UnbanAdminHandler extends RequestHandler<UnbanAdminRequest, StatusResponse> {
    private final IAdministratorService administratorService;

    @Override
    public StatusResponse handle(UnbanAdminRequest request) {
        Administrator administrator = administratorService.getById(request.getId());
        if (administrator == null) {
            throw new InternalException(ResponseCode.ADMIN_IS_NOT_EXISTED);
        }
        administrator.setStatus(AdminStatus.ACTIVE);
        administratorService.save(administrator);
        return new StatusResponse();
    }
}
