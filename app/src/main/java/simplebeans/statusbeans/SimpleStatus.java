package simplebeans.statusbeans;

/**
 * Created by Hp on 11/16/2016.
 */
public class SimpleStatus {
    private int statusCode;
    private String message;

    public SimpleStatus(int statusCode, String message) {
        this.setStatusCode(statusCode);
        this.setMessage(message);
    }

    public SimpleStatus() {

    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
