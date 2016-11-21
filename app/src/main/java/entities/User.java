package entities;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by Hp on 11/15/2016.
 */
public class User extends SugarRecord {
    private int userId;
    private String shiftId;
    private Date startDateTime;
    private Date endDateTime;
    private int status;//ended, active, paused
    private String pin;

    public User(int userId, String shiftId, Date startDateTime, Date endDateTime, int status, String pin) {
        this.setUserId(userId);
        this.setShiftId(shiftId);
        this.setStartDateTime(startDateTime);
        this.setEndDateTime(endDateTime);
        this.setStatus(status);
        this.setPin(pin);
    }

    public User() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
