package simplebeans.registerbeans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Hp on 11/16/2016.
 */
public class RegisterResponseBean {
    @JsonProperty("deviceId")
    private int deviceId;
    @JsonProperty("deviceName")
    private String deviceName;
    @JsonProperty("status")
    private int status;
    @JsonProperty("branchId")
    private int branchId;
    @JsonProperty("branchName")
    private String branchName;
    @JsonProperty("mainMsisdn")
    private String msisdn;
    @JsonProperty("auxMsisdn")
    private String auxMsisdn;

    public RegisterResponseBean(int deviceId, String deviceName, int status, int branchId, String branchName, String msisdn, String auxMsisdn) {
        this.setDeviceId(deviceId);
        this.setDeviceName(deviceName);
        this.setStatus(status);
        this.setBranchId(branchId);
        this.setBranchName(branchName);
        this.setMsisdn(msisdn);
        this.setAuxMsisdn(auxMsisdn);
    }

    public RegisterResponseBean() {

    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getAuxMsisdn() {
        return auxMsisdn;
    }

    public void setAuxMsisdn(String auxMsisdn) {
        this.auxMsisdn = auxMsisdn;
    }
}
