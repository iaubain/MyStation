package simplebeans.statusbeans;

/**
 * Created by Hp on 11/16/2016.
 */
public class StatusUsage {
    private SimpleStatus status;

    public StatusUsage(SimpleStatus status) {
        this.setStatus(status);
    }

    public StatusUsage() {

    }

    public SimpleStatus getStatus() {
        return status;
    }

    public void setStatus(SimpleStatus status) {
        this.status = status;
    }
}
