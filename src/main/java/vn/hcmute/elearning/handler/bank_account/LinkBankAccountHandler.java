package vn.hcmute.elearning.handler.bank_account;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.client.MiddlewareClient;
import vn.hcmute.elearning.client.response.CheckBeneficiaryCoreResponse;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.bank_account.request.LinkBankAccountRequest;
import vn.hcmute.elearning.entity.Bank;
import vn.hcmute.elearning.entity.Teacher;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.IBankService;
import vn.hcmute.elearning.service.interfaces.ITeacherService;

@Component
@RequiredArgsConstructor
public class LinkBankAccountHandler extends RequestHandler<LinkBankAccountRequest, StatusResponse> {
    private final MiddlewareClient middlewareClient;
    private final ITeacherService teacherService;
    private final IBankService bankService;
    @Override
    public StatusResponse handle(LinkBankAccountRequest request) {
        Teacher teacher = teacherService.getByUserId(request.getUserId());
        if (teacher == null) {
            throw new InternalException(ResponseCode.TEACHER_NOT_FOUND);
        }
        CheckBeneficiaryCoreResponse response = middlewareClient.checkBankAccount(request.getBin(), request.getAccountNo());
        if (response == null) {
            throw new InternalException(ResponseCode.GET_BENEFICIARY_FAILED);
        }
        if (!StringUtils.equals(request.getAccountName(), response.getAccountName())) {
            throw new InternalException(ResponseCode.ACCOUNT_NAME_INCORRECT);
        }
        Bank bank = bankService.getByNapasCode(request.getBin());
        teacher.setAccountName(request.getAccountName());
        teacher.setAccountNo(request.getAccountNo());
        teacher.setBin(request.getBin());
        teacherService.save(teacher);
        return new StatusResponse(true);
    }
}
