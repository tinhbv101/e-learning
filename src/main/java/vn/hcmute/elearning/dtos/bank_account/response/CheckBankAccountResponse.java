package vn.hcmute.elearning.dtos.bank_account.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseResponseData;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckBankAccountResponse extends BaseResponseData {
    private String bankAccountName;
}