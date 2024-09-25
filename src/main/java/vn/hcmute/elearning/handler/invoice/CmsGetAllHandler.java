package vn.hcmute.elearning.handler.invoice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.invoice.request.CmsGetAllInvoiceRequest;
import vn.hcmute.elearning.dtos.invoice.response.GetAllInvoiceResponse;
import vn.hcmute.elearning.dtos.invoice.response.InvoiceResponse;
import vn.hcmute.elearning.entity.Administrator;
import vn.hcmute.elearning.entity.Invoice;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.IAdministratorService;
import vn.hcmute.elearning.service.interfaces.IInvoiceService;
import vn.hcmute.elearning.service.specifications.InvoiceSpecification;
import vn.hcmute.elearning.utils.Paginate;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CmsGetAllHandler extends RequestHandler<CmsGetAllInvoiceRequest, GetAllInvoiceResponse> {
    private final InvoiceSpecification invoiceSpecification;
    private final IInvoiceService invoiceService;
    private final IAdministratorService iAdministratorService;

    @Override
    public GetAllInvoiceResponse handle(CmsGetAllInvoiceRequest request) {
        Administrator administrator = iAdministratorService.getById(request.getUserId());
        if (administrator == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        Specification<Invoice> specification = Specification.where(invoiceSpecification.equalStatus(request.getStatus()))
                .and(invoiceSpecification.fromDate(request.fromDatetime(request.getFromDate())))
                .and(invoiceSpecification.toDate(request.toDatetime(request.getToDate())));
        Page<Invoice> page = invoiceService.getAll(specification, request.getPageable());
        List<InvoiceResponse> responseList = page.stream().parallel()
                .map(InvoiceResponse::new)
                .collect(Collectors.toList());
        return new GetAllInvoiceResponse(responseList, new Paginate(page));
    }
}
