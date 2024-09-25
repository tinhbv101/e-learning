package vn.hcmute.elearning.service.course;

import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;
import vn.hcmute.elearning.dtos.invoice.request.AbstractNotifyPaymentRequest;
import vn.hcmute.elearning.enums.PaymentType;

public interface CourseRegisterStrategy {

    PaymentType getPaymentType();

    @Transactional
    String create(@NonNull String courseId, String userId);

    @Transactional
    boolean confirm(@NonNull AbstractNotifyPaymentRequest request, String userId);
}
