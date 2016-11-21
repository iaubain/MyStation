package entities;

import com.orm.SugarRecord;

/**
 * Created by Hp on 11/15/2016.
 */
public class Action extends SugarRecord {
    private int type;
    private String url;
    private int status;
    private String desc;

    public Action(int type, String url, int status, String desc) {
        this.setType(type);
        this.setUrl(url);
        this.setStatus(status);
        this.setDesc(desc);
    }

    public Action() {

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
