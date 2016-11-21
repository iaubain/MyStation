package entities;

import com.orm.SugarRecord;

import java.math.BigDecimal;

/**
 * Created by Hp on 11/15/2016.
 */
public class Nozzle extends SugarRecord {
    private int nozzleId;
    private String nozzleName;
    private BigDecimal nozzleIndex;
    private int pumpId;
    private int productId;
    private String productName;
    private int unitPrice;
    private int statusCode;
    private String userName;
    private int userId;

    public Nozzle(int nozzleId, String nozzleName, BigDecimal nozzleIndex, int pumpId, int productId, String productName, int unitPrice, int statusCode, String userName, int userId) {
        this.setNozzleId(nozzleId);
        this.setNozzleName(nozzleName);
        this.setNozzleIndex(nozzleIndex);
        this.setPumpId(pumpId);
        this.setProductId(productId);
        this.setProductName(productName);
        this.setUnitPrice(unitPrice);
        this.setStatusCode(statusCode);
        this.setUserName(userName);
        this.setUserId(userId);
    }

    public Nozzle() {

    }

    public int getNozzleId() {
        return nozzleId;
    }

    public void setNozzleId(int nozzleId) {
        this.nozzleId = nozzleId;
    }

    public String getNozzleName() {
        return nozzleName;
    }

    public void setNozzleName(String nozzleName) {
        this.nozzleName = nozzleName;
    }

    public BigDecimal getNozzleIndex() {
        return nozzleIndex;
    }

    public void setNozzleIndex(BigDecimal nozzleIndex) {
        this.nozzleIndex = nozzleIndex;
    }

    public int getPumpId() {
        return pumpId;
    }

    public void setPumpId(int pumpId) {
        this.pumpId = pumpId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
