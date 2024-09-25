package vn.hcmute.elearning.handler.bank_account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.bank_account.request.GetLinkedAccountRequest;
import vn.hcmute.elearning.dtos.bank_account.response.BankResponse;
import vn.hcmute.elearning.dtos.bank_account.response.LinkedAccountResponse;
import vn.hcmute.elearning.entity.Bank;
import vn.hcmute.elearning.entity.Teacher;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.IBankService;
import vn.hcmute.elearning.service.interfaces.ITeacherService;

@Component
@RequiredArgsConstructor
public class GetLinkedAccountHandler extends RequestHandler<GetLinkedAccountRequest, LinkedAccountResponse> {
    private final ITeacherService teacherService;
    private final IBankService bankService;

    @Override
    public LinkedAccountResponse handle(GetLinkedAccountRequest request) {
        Teacher teacher = teacherService.getByUserId(request.getUserId());
        if (teacher == null) {
            throw new InternalException(ResponseCode.TEACHER_NOT_FOUND);
        }
        if (teacher.checkLinkedAccount()) {
            Bank bank = bankService.getByNapasCode(teacher.getBin());
            return new LinkedAccountResponse(teacher.getAccountNo(), teacher.getAccountName(), new BankResponse(bank));
        }
        return new LinkedAccountResponse();
    }
}
