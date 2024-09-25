package vn.hcmute.elearning.handler.invoice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.invoice.request.TeacherGetAllInvoiceRequest;
import vn.hcmute.elearning.dtos.invoice.response.GetAllInvoiceResponse;
import vn.hcmute.elearning.dtos.invoice.response.InvoiceResponse;
import vn.hcmute.elearning.entity.Invoice;
import vn.hcmute.elearning.entity.Teacher;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.IInvoiceService;
import vn.hcmute.elearning.service.interfaces.IUserService;
import vn.hcmute.elearning.service.specifications.InvoiceSpecification;
import vn.hcmute.elearning.utils.Paginate;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TeacherGetAllHandler extends RequestHandler<TeacherGetAllInvoiceRequest, GetAllInvoiceResponse> {

    private final IUserService userService;
    private final InvoiceSpecification invoiceSpecification;
    private final IInvoiceService invoiceService;

    @Override
    public GetAllInvoiceResponse handle(TeacherGetAllInvoiceRequest request) {
        User user = userService.getUserById(request.getUserId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        Teacher teacher = user.getTeacher();
        if (teacher == null) {
            throw new InternalException(ResponseCode.USER_INVALID);
        }
        Specification<Invoice> specification = Specification.where(invoiceSpecification.equalTeacherId(teacher.getId()))
            .and(invoiceSpecification.equalStatus(request.getStatus()))
            .and(invoiceSpecification.fromDate(request.fromDatetime(request.getFromDate())))
            .and(invoiceSpecification.toDate(request.toDatetime(request.getToDate())));
        Page<Invoice> page = invoiceService.getAll(specification, request.getPageable());
        List<InvoiceResponse> responseList = page.stream().parallel()
            .map(InvoiceResponse::new)
            .collect(Collectors.toList());
        return new GetAllInvoiceResponse(responseList, new Paginate(page));
    }
}
