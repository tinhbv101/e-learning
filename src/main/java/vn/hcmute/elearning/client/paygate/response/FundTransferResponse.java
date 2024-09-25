package vn.hcmute.elearning.client.paygate.response;

import lombok.*;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.enums.FTStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FundTransferResponse extends BaseResponseData {

    private String referenceNumber;
    private String txnNumber;
    private FTStatus status;
    private String transactionTime;

}