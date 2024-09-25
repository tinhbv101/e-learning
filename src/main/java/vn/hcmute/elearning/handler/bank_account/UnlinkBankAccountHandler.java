package vn.hcmute.elearning.handler.bank_account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.bank_account.request.UnlinkBankAccountRequest;
import vn.hcmute.elearning.entity.Teacher;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.ITeacherService;

@Component
@RequiredArgsConstructor
public class UnlinkBankAccountHandler extends RequestHandler<UnlinkBankAccountRequest, StatusResponse> {

    private final ITeacherService teacherService;

    @Override
    public StatusResponse handle(UnlinkBankAccountRequest request) {
        Teacher teacher = teacherService.getByUserId(request.getUserId());
        if (teacher.checkLinkedAccount()) {
            teacher.setAccountNo(null)
                .setAccountName(null)
                .setBin(null);
            teacherService.save(teacher);
            return new StatusResponse(true);
        }
        throw new InternalException(ResponseCode.BANK_ACCOUNT_NOT_FOUNT);
    }
}
