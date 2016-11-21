package entities;

import com.orm.SugarRecord;

/**
 * Created by Hp on 11/16/2016.
 */
public class Receipt extends SugarRecord {
    private String transactionId;
    private boolean directReceipt;
    private boolean isReceiptGenerated;
    private int prints;

    public Receipt(String transactionId, boolean directReceipt, boolean isReceiptGenerated, int prints) {
        this.setTransactionId(transactionId);
        this.setDirectReceipt(directReceipt);
        this.setReceiptGenerated(isReceiptGenerated);
        this.setPrints(prints);
    }

    public Receipt() {

    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public boolean isDirectReceipt() {
        return directReceipt;
    }

    public void setDirectReceipt(boolean directReceipt) {
        this.directReceipt = directReceipt;
    }

    public boolean isReceiptGenerated() {
        return isReceiptGenerated;
    }

    public void setReceiptGenerated(boolean receiptGenerated) {
        isReceiptGenerated = receiptGenerated;
    }

    public int getPrints() {
        return prints;
    }

    public void setPrints(int prints) {
        this.prints = prints;
    }
}
