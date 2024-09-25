package vn.hcmute.elearning.client.paygate.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;
import vn.hcmute.elearning.core.BaseRequestData;
import vn.hcmute.elearning.enums.FTMethod;
import vn.hcmute.elearning.enums.FTTarget;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FundTransferRequest extends BaseRequestData {

    @NotBlank
    private String referenceNumber;

    @NotNull
    private FTMethod method;

    @NotBlank
    private String accountNo;

    @NotBlank
    private String toAccountNo;

    @NotNull
    private FTTarget target;

    @NotBlank
    private String bin;

    @NotNull
    private BigDecimal amount;

    @NotBlank
    private String description;
}
