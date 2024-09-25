package vn.hcmute.elearning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.hcmute.elearning.controller.interfaces.IInvoiceController;
import vn.hcmute.elearning.core.BaseController;
import vn.hcmute.elearning.core.BaseRequestData;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.core.ResponseBase;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.invoice.request.*;
import vn.hcmute.elearning.dtos.invoice.response.GetAllInvoiceResponse;
import vn.hcmute.elearning.dtos.invoice.response.GetAllWithdrawInvoiceResponse;
import vn.hcmute.elearning.enums.PaymentType;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.course.CourseRegisterStrategy;
import vn.hcmute.elearning.service.interfaces.ICourseService;
import vn.unicloud.sdk.payment.KPayClient;
import vn.unicloud.sdk.payment.client.EncryptedBodyRequest;
import vn.unicloud.sdk.payment.security.PackedMessage;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class InvoiceController extends BaseController implements IInvoiceController {
    private final KPayClient kPayClient;
    private final ICourseService courseService;

    private <T extends BaseRequestData, I extends BaseResponseData> ResponseEntity<ResponseBase<String>> execute(
            String clientId,
            String signature,
            Long timestamp,
            String encryptedData,
            Class<T> requestType) {

        PackedMessage packedMessage = PackedMessage.builder()
                .clientId(clientId)
                .timestamp(timestamp)
                .signature(signature)
                .encryptedData(encryptedData)
                .build();
        T request = kPayClient.getKPayPacker().decode(packedMessage, requestType);
        request.setClientId(clientId);
        ResponseEntity<ResponseBase<I>> response = this.execute(request);
        ResponseBase<I> body = response.getBody();
        if (body == null) {
            throw new InternalException(ResponseCode.TRANSACTION_FAILED);
        }
        I data = body.getData();
        PackedMessage packedResponse = kPayClient.getKPayPacker().encode(data);
        return this.buildResponse(packedResponse);
    }

    @Override
    public ResponseEntity<ResponseBase<String>> notifyInvoice(String clientId, String signature, Long timestamp, EncryptedBodyRequest request) {
        return this.execute(clientId, signature, timestamp, request.getData(), NotifyKLBPayRequest.class);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> notifyVNPay(Map<String, String> request) {
        CourseRegisterStrategy registerStrategy = courseService.getRegisterStrategy(PaymentType.VNPAY);
        if (registerStrategy.confirm(new NotifyVNPayRequest(request), null)) {
            return ResponseEntity.ok(new ResponseBase<>(new StatusResponse(true)));
        }
        return ResponseEntity.ok(new ResponseBase<>(new StatusResponse(true)));
    }

    @Override
    public ResponseEntity<ResponseBase<GetAllInvoiceResponse>> getAll(GetAllInvoiceRequest request, Pageable pageable) {
        request.setPageable(pageable);
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<GetAllInvoiceResponse>> teacherGetAll(TeacherGetAllInvoiceRequest request, Pageable pageable) {
        request.setPageable(pageable);
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<GetAllWithdrawInvoiceResponse>> teacherGetAllWithdrawInvoice(TeacherGetAllWithdrawInvoiceRequest request, Pageable pageable) {
        request.setPageable(pageable);
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<GetAllWithdrawInvoiceResponse>> cmsGetAllWithdrawInvoice(CmsGetAllWithdrawInvoiceRequest request, Pageable pageable) {
        request.setPageable(pageable);
        return this.execute(request);
    }

    @Override
    public ResponseEntity<ResponseBase<GetAllInvoiceResponse>> cmsGetAll(CmsGetAllInvoiceRequest request, Pageable pageable) {
        request.setPageable(pageable);
        return this.execute(request);
    }


}
