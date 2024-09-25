package vn.hcmute.elearning.handler.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.admin.request.UpdateInfoAdminRequest;
import vn.hcmute.elearning.dtos.admin.response.UpdateInfoAdminResponse;
import vn.hcmute.elearning.entity.Administrator;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.IAdminMapper;
import vn.hcmute.elearning.service.interfaces.IAdministratorService;

@Component
@RequiredArgsConstructor
public class UpdateInfoAdminHandler extends RequestHandler<UpdateInfoAdminRequest, UpdateInfoAdminResponse> {
    private final IAdministratorService administratorService;
    private final IAdminMapper adminMapper;

    @Override
    public UpdateInfoAdminResponse handle(UpdateInfoAdminRequest request) {
        Administrator administrator = administratorService.getById(request.getId());
        if (administrator == null) {
            throw new InternalException(ResponseCode.ADMIN_IS_NOT_EXISTED);
        }
        administrator.setEmail(request.getEmail());
        administrator.setFullName(request.getFullName());

        Administrator administratorSave = administratorService.save(administrator);
        return new UpdateInfoAdminResponse(adminMapper.toAdministratorDto(administratorSave));
    }
}
