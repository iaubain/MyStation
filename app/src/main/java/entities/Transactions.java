package entities;

import com.orm.SugarRecord;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Hp on 11/16/2016.
 */
public class Transactions extends SugarRecord {
    private String transactionId;
    private String externalId;
    private int userId;
    private String shiftId;
    private int branchId;
    private int nozzleId;
    private String deviceName;
    private int productId;
    private BigDecimal amount;
    private BigDecimal quantity;
    private String status;
    private Date transactionDate;

    public Transactions(String transactionId, String externalId, int userId, String shiftId, int branchId, int nozzleId, String deviceName, int productId, BigDecimal amount, BigDecimal quantity, String status, Date transactionDate) {
        this.setTransactionId(transactionId);
        this.setExternalId(externalId);
        this.setUserId(userId);
        this.setShiftId(shiftId);
        this.setBranchId(branchId);
        this.setNozzleId(nozzleId);
        this.setDeviceName(deviceName);
        this.setProductId(productId);
        this.setAmount(amount);
        this.setQuantity(quantity);
        this.setStatus(status);
        this.setTransactionDate(transactionDate);
    }

    public Transactions() {

    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getNozzleId() {
        return nozzleId;
    }

    public void setNozzleId(int nozzleId) {
        this.nozzleId = nozzleId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
