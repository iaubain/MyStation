package entities;

import com.orm.SugarRecord;

/**
 * Created by Hp on 11/16/2016.
 */
public class MyStation extends SugarRecord {
    private int userId;
    private int pumpId;

    public MyStation(int userId, int pumpId) {
        this.setUserId(userId);
        this.setPumpId(pumpId);
    }

    public MyStation() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPumpId() {
        return pumpId;
    }

    public void setPumpId(int pumpId) {
        this.pumpId = pumpId;
    }
}
