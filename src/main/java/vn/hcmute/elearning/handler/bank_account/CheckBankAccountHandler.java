package vn.hcmute.elearning.handler.bank_account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.client.MiddlewareClient;
import vn.hcmute.elearning.client.response.CheckBeneficiaryCoreResponse;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.bank_account.request.CheckBankAccountRequest;
import vn.hcmute.elearning.dtos.bank_account.response.CheckBankAccountResponse;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.IBankService;

@Component
@RequiredArgsConstructor
public class CheckBankAccountHandler extends RequestHandler<CheckBankAccountRequest, CheckBankAccountResponse> {
    private final MiddlewareClient middlewareClient;
    private final IBankService bankService;
    @Override
    public CheckBankAccountResponse handle(CheckBankAccountRequest request) {
        bankService.getByNapasCode(request.getBin());
        CheckBeneficiaryCoreResponse response = middlewareClient.checkBankAccount(request.getBin(), request.getAccountNo());
        if (response == null) {
            throw new InternalException(ResponseCode.FAILED);
        }
        return new CheckBankAccountResponse(response.getAccountName());
    }
}
