package vn.hcmute.elearning.handler.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.admin.request.GetInfoAdminByIdRequest;
import vn.hcmute.elearning.dtos.admin.response.GetInfoAdminResponse;
import vn.hcmute.elearning.entity.Administrator;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.IAdminMapper;
import vn.hcmute.elearning.service.interfaces.IAdministratorService;

@Component
@RequiredArgsConstructor
public class GetInfoAdminByIdHandler extends RequestHandler<GetInfoAdminByIdRequest, GetInfoAdminResponse> {
    private final IAdministratorService administratorService;
    private final IAdminMapper adminMapper;

    @Override
    public GetInfoAdminResponse handle(GetInfoAdminByIdRequest request) {
        Administrator administrator = administratorService.getById(request.getId());
        if (administrator == null) {
            throw new InternalException(ResponseCode.ADMIN_IS_NOT_EXISTED);
        }
        return new GetInfoAdminResponse(adminMapper.toAdministratorDto(administrator));
    }
}
