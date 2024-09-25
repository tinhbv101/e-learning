package vn.hcmute.elearning.handler.invoice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.invoice.request.TeacherGetAllWithdrawInvoiceRequest;
import vn.hcmute.elearning.dtos.invoice.response.GetAllWithdrawInvoiceResponse;
import vn.hcmute.elearning.dtos.teacher.response.WithdrawResponse;
import vn.hcmute.elearning.entity.Teacher;
import vn.hcmute.elearning.entity.WithdrawInvoice;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.ITeacherService;
import vn.hcmute.elearning.service.interfaces.IWithdrawInvoiceService;
import vn.hcmute.elearning.service.specifications.WithdrawInvoiceSpecification;
import vn.hcmute.elearning.utils.Paginate;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetAllWithdrawInvoiceHandler extends RequestHandler<TeacherGetAllWithdrawInvoiceRequest, GetAllWithdrawInvoiceResponse> {
    private final WithdrawInvoiceSpecification specification;
    private final IWithdrawInvoiceService withdrawInvoiceService;
    private final ITeacherService teacherService;
    @Override
    public GetAllWithdrawInvoiceResponse handle(TeacherGetAllWithdrawInvoiceRequest request) {
        Teacher teacher = teacherService.getByUserId(request.getUserId());
        if (teacher == null) {
            throw new InternalException(ResponseCode.TEACHER_NOT_FOUND);
        }
        Specification<WithdrawInvoice> spec = Specification.where(specification.equalTeacherId(teacher.getId()))
            .and(specification.equalStatus(request.getStatus()))
            .and(specification.likeTxnNumber(request.getTxnNumber()))
            .and(specification.fromDate(request.fromDatetime(request.getFromDate())))
            .and(specification.toDate(request.toDatetime(request.getToDate())));
        Page<WithdrawInvoice> page = withdrawInvoiceService.getAll(spec, request.getPageable());
        List<WithdrawResponse> list = page.getContent().stream()
            .map(withdrawInvoice -> new WithdrawResponse(withdrawInvoice, null))
            .collect(Collectors.toList());
        return new GetAllWithdrawInvoiceResponse(list, new Paginate(page));
    }
}
