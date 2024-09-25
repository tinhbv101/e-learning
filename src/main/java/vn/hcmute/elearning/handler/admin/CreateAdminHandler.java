package vn.hcmute.elearning.handler.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.admin.request.CreateAdminRequest;
import vn.hcmute.elearning.dtos.admin.response.CreateAdminResponse;
import vn.hcmute.elearning.entity.Administrator;
import vn.hcmute.elearning.enums.AdminStatus;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.enums.Role;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.IAdminMapper;
import vn.hcmute.elearning.service.KeycloakService;
import vn.hcmute.elearning.service.interfaces.IAdministratorService;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateAdminHandler extends RequestHandler<CreateAdminRequest, CreateAdminResponse> {
    private final IAdministratorService administratorService;
    private final KeycloakService keycloakService;
    private final IAdminMapper adminMapper;

    @Override
    public CreateAdminResponse handle(CreateAdminRequest request) {
        Administrator administrator = administratorService.getByPhone(request.getPhone());
        if (administrator != null) {
            throw new InternalException(ResponseCode.ADMIN_IS_EXISTED);
        }
        String userId = keycloakService.createUser(request.getPhone(), request.getPassword(), request.getEmail(), request.getFullName(), "", Role.ADMIN);
        if (userId == null) {
            throw new InternalException(ResponseCode.ADMIN_CREATE_FAILED);
        }
        administrator = new Administrator()
                .setId(userId)
                .setEmail(request.getEmail())
                .setFullName(request.getFullName())
                .setPhone(request.getPhone())
                .setStatus(AdminStatus.ACTIVE);
        Administrator administratorSave = administratorService.save(administrator);

        return new CreateAdminResponse(adminMapper.toAdministratorDto(administratorSave));
    }
}
