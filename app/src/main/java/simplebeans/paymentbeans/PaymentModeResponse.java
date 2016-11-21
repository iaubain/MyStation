package simplebeans.paymentbeans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Hp on 11/18/2016.
 */
public class PaymentModeResponse {
    @JsonProperty("PaymentMode")
    private List<PaymentResponseBean> paymentModeList;
    @JsonProperty("message")
    private String message;
    @JsonProperty("statusCode")
    private int statusCode;

    public PaymentModeResponse(List<PaymentResponseBean> paymentModeList, String message, int statusCode) {
        this.setPaymentModeList(paymentModeList);
        this.setMessage(message);
        this.setStatusCode(statusCode);
    }

    public PaymentModeResponse() {

    }

    public List<PaymentResponseBean> getPaymentModeList() {
        return paymentModeList;
    }

    public void setPaymentModeList(List<PaymentResponseBean> paymentModeList) {
        this.paymentModeList = paymentModeList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
