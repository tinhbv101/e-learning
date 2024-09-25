package vn.hcmute.elearning.handler.invoice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.invoice.request.CmsGetAllWithdrawInvoiceRequest;
import vn.hcmute.elearning.dtos.invoice.response.GetAllWithdrawInvoiceResponse;
import vn.hcmute.elearning.dtos.teacher.response.WithdrawResponse;
import vn.hcmute.elearning.entity.WithdrawInvoice;
import vn.hcmute.elearning.mapper.IUserMapper;
import vn.hcmute.elearning.service.interfaces.IWithdrawInvoiceService;
import vn.hcmute.elearning.service.specifications.WithdrawInvoiceSpecification;
import vn.hcmute.elearning.utils.Paginate;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CmsGetAllWithdrawInvoiceHandler extends RequestHandler<CmsGetAllWithdrawInvoiceRequest, GetAllWithdrawInvoiceResponse> {
    private final WithdrawInvoiceSpecification specification;
    private final IWithdrawInvoiceService withdrawInvoiceService;
    private final IUserMapper iUserMapper;
    @Override
    public GetAllWithdrawInvoiceResponse handle(CmsGetAllWithdrawInvoiceRequest request) {
        Specification<WithdrawInvoice> spec = Specification.where(specification.equalTeacherId(request.getTeacherId()))
            .and(specification.equalStatus(request.getStatus()))
            .and(specification.likeTxnNumber(request.getTxnNumber()))
            .and(specification.fromDate(request.fromDatetime(request.getFromDate())))
            .and(specification.toDate(request.toDatetime(request.getToDate())));
        Page<WithdrawInvoice> page = withdrawInvoiceService.getAll(spec, request.getPageable());
        List<WithdrawResponse> list = page.getContent().stream()
            .map(withdrawInvoice -> new WithdrawResponse(withdrawInvoice, iUserMapper))
            .collect(Collectors.toList());
        return new GetAllWithdrawInvoiceResponse(list, new Paginate(page));
    }
}
