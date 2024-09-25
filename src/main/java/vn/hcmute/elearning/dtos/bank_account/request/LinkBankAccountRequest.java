package vn.hcmute.elearning.dtos.bank_account.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseRequestData;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkBankAccountRequest extends BaseRequestData {
    private String accountNo;
    private String bin;
    private String accountName;
}
