package simplebeans.loginbeans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Hp on 11/16/2016.
 */
public class LoginRequest {
    @JsonProperty("deviceId")
    private String deviceId;
    @JsonProperty("userPin")
    private String userPin;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserPin() {
        return userPin;
    }

    public void setUserPin(String userPin) {
        this.userPin = userPin;
    }

    public LoginRequest(String deviceId, String userPin) {

        this.deviceId = deviceId;
        this.userPin = userPin;
    }

    public LoginRequest() {

    }
}
