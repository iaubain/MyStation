package simplebeans.registerbeans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Hp on 11/16/2016.
 */
public class RegisterRequest {
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("deviceId")
    private String deviceName;
    @JsonProperty("serialNumber")
    private String serialNumber;
    @JsonProperty("mainMsisdn")
    private String msisdn;
    @JsonProperty("auxMsisdn")
    private String auxMsisdn;

    public RegisterRequest(String email, String password, String deviceName, String serialNumber, String msisdn, String auxMsisdn) {
        this.setEmail(email);
        this.setPassword(password);
        this.setDeviceName(deviceName);
        this.setSerialNumber(serialNumber);
        this.setMsisdn(msisdn);
        this.setAuxMsisdn(auxMsisdn);
    }

    public RegisterRequest() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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
