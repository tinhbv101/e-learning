package vn.hcmute.elearning.handler.invoice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.invoice.request.NotifyKLBPayRequest;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.entity.Invoice;
import vn.hcmute.elearning.entity.Teacher;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.InvoiceStatus;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.ICourseService;
import vn.hcmute.elearning.service.interfaces.IInvoiceService;
import vn.hcmute.elearning.service.interfaces.ITeacherService;
import vn.hcmute.elearning.service.interfaces.IUserService;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class NotifyInvoiceHandler extends RequestHandler<NotifyKLBPayRequest, StatusResponse> {
    private final IInvoiceService invoiceService;
    private final ICourseService courseService;
    private final IUserService userService;
    private final ITeacherService teacherService;
    @Override
    @Transactional
    public StatusResponse handle(NotifyKLBPayRequest request) {
        Invoice invoice = invoiceService.getByCode(request.getRefTransactionId());
        if (InvoiceStatus.SUCCESS.equals(invoice.getStatus())) {
            throw new InternalException(ResponseCode.INVOICE_NOT_FOUND);
        }
        if (request.isSuccess()) {
            invoice.setStatus(InvoiceStatus.SUCCESS);
            invoice.setTime(getTime(request.getTime()));
        } else {
            invoice.setStatus(InvoiceStatus.CANCELED);
            invoice.setTime(getTime(request.getTime()));
        }

        invoice = invoiceService.save(invoice);
        if (InvoiceStatus.SUCCESS.equals(invoice.getStatus())) {
            Course course = invoice.getCourse();
            User user = invoice.getUser();
            course.getUsers().add(user);
            user.getCourses().add(course);
            Teacher teacher = course.getTeacher();
            if (teacher == null) {
                throw new InternalException(ResponseCode.TEACHER_NOT_FOUND);
            }
            teacher.setBalance(teacher.getBalance() + invoice.getAmount());
            teacherService.save(teacher);
            courseService.updateCourse(course);
            userService.updateUser(user);
        }
        return new StatusResponse(true);
    }
    private LocalDateTime getTime(String time) {
        try {
            return LocalDateTime.parse(time);
        } catch (RuntimeException e) {
            return LocalDateTime.now();
        }
    }
}
