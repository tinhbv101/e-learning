package vn.hcmute.elearning.client.paygate.response;

import lombok.*;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.enums.FTStatus;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QueryTransferResponse extends BaseResponseData {

    private String referenceNumber;
    private String txnNumber;
    private FTStatus status;
    private BigDecimal amount;
    private String description;
    private String transactionTime;

}
