package vn.hcmute.elearning.dtos.invoice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.dtos.teacher.response.WithdrawResponse;
import vn.hcmute.elearning.utils.Paginate;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllWithdrawInvoiceResponse extends BaseResponseData {
    private List<WithdrawResponse> invoices;
    private Paginate paginate;
}
