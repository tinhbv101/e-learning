package vn.hcmute.elearning.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.hcmute.elearning.controller.interfaces.IBankAccountController;
import vn.hcmute.elearning.core.BaseController;
import vn.hcmute.elearning.core.ResponseBase;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.bank_account.request.*;
import vn.hcmute.elearning.dtos.bank_account.response.CheckBankAccountResponse;
import vn.hcmute.elearning.dtos.bank_account.response.GetAllBanksResponse;
import vn.hcmute.elearning.dtos.bank_account.response.LinkedAccountResponse;

@RestController
public class BankAccountController extends BaseController implements IBankAccountController {
    @Override
    public ResponseEntity<ResponseBase<GetAllBanksResponse>> getAllBanks(GetAllBanksRequest request, Pageable pageable) {
        request.setPageable(pageable);
        return this.execute(request, GetAllBanksResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<CheckBankAccountResponse>> checkBankAccount(CheckBankAccountRequest request) {
        return this.execute(request, CheckBankAccountResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> linkBankAccount(LinkBankAccountRequest request) {
        return this.execute(request, StatusResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<LinkedAccountResponse>> getLinkedAccount() {
        return this.execute(new GetLinkedAccountRequest(), LinkedAccountResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> unlinkBankAccount() {
        return this.execute(new UnlinkBankAccountRequest(), StatusResponse.class);
    }


}
