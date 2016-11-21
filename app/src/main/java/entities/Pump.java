package entities;

import com.orm.SugarRecord;

/**
 * Created by Hp on 11/15/2016.
 */
public class Pump extends SugarRecord {
    private int pumpId;
    private String pumpName;

    public Pump(int pumpId, String pumpName) {
        this.setPumpId(pumpId);
        this.setPumpName(pumpName);
    }

    public Pump() {

    }

    public int getPumpId() {
        return pumpId;
    }

    public void setPumpId(int pumpId) {
        this.pumpId = pumpId;
    }

    public String getPumpName() {
        return pumpName;
    }

    public void setPumpName(String pumpName) {
        this.pumpName = pumpName;
    }
}
