package simplebeans.pumpbeans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Hp on 11/18/2016.
 */
public class PumpResponse {
    @JsonProperty("PumpDetailsModel")
    private List<PumpResponseBean> pumps;
    @JsonProperty("message")
    private String message;
    @JsonProperty("statusCode")
    private int statusCode;

    public PumpResponse(List<PumpResponseBean> pumps, String message, int statusCode) {
        this.setPumps(pumps);
        this.setMessage(message);
        this.setStatusCode(statusCode);
    }

    public PumpResponse() {

    }

    public List<PumpResponseBean> getPumps() {
        return pumps;
    }

    public void setPumps(List<PumpResponseBean> pumps) {
        this.pumps = pumps;
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
}
