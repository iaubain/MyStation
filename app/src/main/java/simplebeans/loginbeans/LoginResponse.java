package simplebeans.loginbeans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import simplebeans.statusbeans.SimpleStatus;

/**
 * Created by Hp on 11/16/2016.
 */
public class LoginResponse {
    @JsonProperty("LoginOpModel")
    private LoginResponseBean loginResponseBean;
    @JsonProperty("message")
    private String message;
    @JsonProperty("statusCode")
    private int statusCode;
    @JsonIgnore
    private
    SimpleStatus status;

    public LoginResponse(LoginResponseBean loginResponseBean, String message, int statusCode, SimpleStatus status) {
        this.setLoginResponseBean(loginResponseBean);
        this.setMessage(message);
        this.setStatusCode(statusCode);
        this.setStatus(status);
    }

    public LoginResponse() {

    }

    public LoginResponseBean getLoginResponseBean() {
        return loginResponseBean;
    }

    public void setLoginResponseBean(LoginResponseBean loginResponseBean) {
        this.loginResponseBean = loginResponseBean;
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
