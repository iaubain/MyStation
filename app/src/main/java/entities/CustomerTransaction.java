package entities;

import com.orm.SugarRecord;

/**
 * Created by Hp on 11/16/2016.
 */
public class CustomerTransaction extends SugarRecord {
    private String transactionId;
    private String numberPlate;
    private String name;
    private String tin;

    public CustomerTransaction(String transactionId, String numberPlate, String name, String tin) {
        this.setTransactionId(transactionId);
        this.setNumberPlate(numberPlate);
        this.setName(name);
        this.setTin(tin);
    }

    public CustomerTransaction() {

    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }
}
