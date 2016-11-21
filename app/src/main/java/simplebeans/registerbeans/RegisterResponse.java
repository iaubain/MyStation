package simplebeans.registerbeans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import simplebeans.statusbeans.SimpleStatus;

/**
 * Created by Hp on 11/16/2016.
 */
public class RegisterResponse {
    @JsonProperty("DeviceRegistrationResponse")
    private RegisterResponseBean devdata;
    @JsonProperty("message")
    private String message;
    @JsonProperty("statusCode")
    private int statusCode;
    @JsonIgnore
    private
    SimpleStatus status;

    public RegisterResponse(RegisterResponseBean devdata, String message, int statusCode, SimpleStatus status) {
        this.setDevdata(devdata);
        this.setMessage(message);
        this.setStatusCode(statusCode);
        this.setStatus(status);
    }

    public RegisterResponse() {

    }

    public RegisterResponseBean getDevdata() {
        return devdata;
    }

    public void setDevdata(RegisterResponseBean devdata) {
        this.devdata = devdata;
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

    public SimpleStatus getStatus() {
        return status;
    }

    public void setStatus(SimpleStatus status) {
        this.status = status;
    }
}
