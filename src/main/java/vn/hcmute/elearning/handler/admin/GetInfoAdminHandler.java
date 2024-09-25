package vn.hcmute.elearning.handler.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.admin.request.GetInfoAdminRequest;
import vn.hcmute.elearning.dtos.admin.response.GetInfoAdminResponse;
import vn.hcmute.elearning.entity.Administrator;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.IAdminMapper;
import vn.hcmute.elearning.service.interfaces.IAdministratorService;

@Component
@RequiredArgsConstructor
public class GetInfoAdminHandler extends RequestHandler<GetInfoAdminRequest, GetInfoAdminResponse> {
    private final IAdministratorService administratorService;
    private final IAdminMapper adminMapper;

    @Override
    public GetInfoAdminResponse handle(GetInfoAdminRequest request) {
        Administrator administrator = administratorService.getById(request.getUserId());
        if (administrator == null) {
            throw new InternalException(ResponseCode.ADMIN_IS_NOT_EXISTED);
        }
        return new GetInfoAdminResponse(adminMapper.toAdministratorDto(administrator));
    }
}
