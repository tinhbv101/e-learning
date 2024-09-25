package vn.hcmute.elearning.dtos.invoice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.hcmute.elearning.entity.Course;
import vn.hcmute.elearning.entity.Invoice;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.InvoiceStatus;
import vn.hcmute.elearning.model.UserResponse;
import vn.hcmute.elearning.model.course.CourseDto;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class InvoiceResponse {
    private String id;
    private UserResponse user;
    private CourseDto course;
    private String payTransactionId;
    private String url;
    private LocalDateTime time;
    private InvoiceStatus status;
    private Long amount;
    private String code;

    public InvoiceResponse(Invoice invoice) {
        this.id = invoice.getId();
        User iUser = invoice.getUser();
        if (iUser != null) {
            this.user = new UserResponse(iUser);
        }
        Course iCourse = invoice.getCourse();
        if (iCourse != null) {
            this.course = new CourseDto(invoice.getCourse());
        }
        this.payTransactionId = invoice.getPayTransactionId();
        this.url = invoice.getUrl();
        this.time = invoice.getTime();
        this.status = invoice.getStatus();
        this.amount = invoice.getAmount();
        this.code = invoice.getCode();
    }
}
