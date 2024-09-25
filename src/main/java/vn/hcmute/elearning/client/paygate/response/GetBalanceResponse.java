package vn.hcmute.elearning.client.paygate.response;

import lombok.*;
import vn.hcmute.elearning.core.BaseResponseData;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetBalanceResponse extends BaseResponseData {

    private String accountNumber;

    private String accountName;

    private String currency;

    private BigDecimal balance;

}