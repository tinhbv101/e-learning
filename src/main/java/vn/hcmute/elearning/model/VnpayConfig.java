package vn.hcmute.elearning.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class VnpayConfig {
    public static final String IP_DEFAULT = "0:0:0:0:0:0:0:1";
    public static final String VERSION_VNPAY = "2.1.0";
    public static final String COMMAND = "pay";
    public static final String ORDER_TYPE = "190000"; //loại đơn hàng Giải trí && Đào tạo
    public static final String TMN_CODE = "MX3POHXV";
    public static final String CURR_CODE = "VND";
    public static final String LOCAL_DEFAULT = "vn";
    public static final String RETURN_URL = "http://localhost:8000/courses/register/payment/return";
    public static final String CHECKSUM = "";
    public static final String VNPAY_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static final String RETURN_MERCHANT_URL = "http://localhost:3000";

}
