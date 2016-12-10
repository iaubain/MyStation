package utilities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hp on 11/24/2016.
 */
public class MySession implements Parcelable {
    public static final Parcelable.Creator<MySession> CREATOR = new Parcelable.Creator<MySession>() {

        public MySession createFromParcel(Parcel in) {
            return new MySession(in);
        }

        public MySession[] newArray(int size) {
            return new MySession[size];
        }
    };

    private int userId;
    private String userName;
    private int branchId;
    private String branchName;
    private String shiftId;
    private String token;

    MySession() {
    }

    public MySession(int userId, String userName, int branchId, String branchName, String shiftId, String token) {
        this.setUserId(userId);
        this.setUserName(userName);
        this.setBranchId(branchId);
        this.setBranchName(branchName);
        this.setShiftId(shiftId);
        this.setToken(token);
    }

    private MySession(Parcel in) {
        userId=in.readInt();
        userName=in.readString();
        branchId=in.readInt();
        branchName=in.readString();
        shiftId=in.readString();
        token=in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getUserId());
        dest.writeString(getUserName());
        dest.writeInt(getBranchId());
        dest.writeString(getBranchName());
        dest.writeString(getShiftId());
        dest.writeString(getToken());
    }
    @Override
    public int describeContents() {
        return 0;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
