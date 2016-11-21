package entities;

import com.orm.SugarRecord;

/**
 * Created by Hp on 11/16/2016.
 */
public class PaymentTransactions extends SugarRecord {
    private String transactionId;
    private int paymentId;
    private String paymentData;// "voucher:123456678; cardNumber:1234539632"

    public PaymentTransactions(String transactionId, int paymentId, String paymentData) {
        this.setTransactionId(transactionId);
        this.setPaymentId(paymentId);
        this.setPaymentData(paymentData);
    }

    public PaymentTransactions() {

    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(String paymentData) {
        this.paymentData = paymentData;
    }
}
