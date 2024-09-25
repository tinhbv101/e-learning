package vn.hcmute.elearning.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.hcmute.elearning.controller.interfaces.IAdministratorController;
import vn.hcmute.elearning.core.BaseController;
import vn.hcmute.elearning.core.PageResponseData;
import vn.hcmute.elearning.core.ResponseBase;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.admin.request.*;
import vn.hcmute.elearning.dtos.admin.response.CreateAdminResponse;
import vn.hcmute.elearning.dtos.admin.response.GetInfoAdminResponse;
import vn.hcmute.elearning.dtos.admin.response.UpdateInfoAdminResponse;
import vn.hcmute.elearning.model.admin.AdministratorDto;

@RestController
public class AdministratorController extends BaseController implements IAdministratorController {

    @Override
    public ResponseEntity<ResponseBase<CreateAdminResponse>> createAdmin(CreateAdminRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> banAdmin(BanAdminRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> unbanAdmin(UnbanAdminRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> resetPasswordAdmin(ResetPasswordAdminRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<UpdateInfoAdminResponse>> updateInfoAdmin(UpdateInfoAdminRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<GetInfoAdminResponse>> getInfoAdminById(String id) {
        GetInfoAdminByIdRequest request = new GetInfoAdminByIdRequest(id);
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<GetInfoAdminResponse>> getInfoAdmin() {
        GetInfoAdminRequest request = new GetInfoAdminRequest();
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<PageResponseData<AdministratorDto>>> getListAdministrator(GetListAdminRequest request, Pageable pageable) {
        request.setPageable(pageable);
        return this.execute(request);
    }
}
