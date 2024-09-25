package vn.hcmute.elearning.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.hcmute.elearning.controller.interfaces.ITeacherController;
import vn.hcmute.elearning.core.BaseController;
import vn.hcmute.elearning.core.PageResponseData;
import vn.hcmute.elearning.core.ResponseBase;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.teacher.request.WithDrawRequest;
import vn.hcmute.elearning.dtos.teacher.response.TeacherGetBalanceResponse;
import vn.hcmute.elearning.dtos.teacher.response.WithdrawResponse;
import vn.hcmute.elearning.dtos.teacher.request.*;
import vn.hcmute.elearning.dtos.teacher.response.CreateRequestRoleTeacherResponse;
import vn.hcmute.elearning.dtos.teacher.response.GetInfoTeacherResponse;
import vn.hcmute.elearning.model.teacher.TeacherDto;

@RestController
public class TeacherController extends BaseController implements ITeacherController {

    @Override
    public ResponseEntity<ResponseBase<CreateRequestRoleTeacherResponse>> createRequestRoleTeacher(CreateRequestRoleTeacherRequest request) {
        return this.execute(request, CreateRequestRoleTeacherResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> approveRequestRoleTeacher(ApproveRequestRoleTeacherRequest request) {
        return this.execute(request, StatusResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<GetInfoTeacherResponse>> getDetail(GetDetailTeacherByIdRequest request) {
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> rejectRequestRoleTeacher(RejectRequestRoleTeacherRequest request) {
        return this.execute(request, StatusResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<PageResponseData<TeacherDto>>> getListRequestRoleTeacher(GetListTeacherRequest request, Pageable pageable) {
        request.setPageable(pageable);
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<GetInfoTeacherResponse>> getInfo() {
        return this.execute(new GetInfoTeacherRequest(), GetInfoTeacherResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<GetInfoTeacherResponse>> getTeacherInfo() {
        return this.execute(new GetInfoTeacherPortalRequest());
    }

    @Override
    public ResponseEntity<ResponseBase<WithdrawResponse>> withDraw(WithDrawRequest request) {
        return this.execute(request, WithdrawResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<TeacherGetBalanceResponse>> getBalance() {
        return this.execute(new TeacherGetBalanceRequest(), TeacherGetBalanceResponse.class);
    }

}
