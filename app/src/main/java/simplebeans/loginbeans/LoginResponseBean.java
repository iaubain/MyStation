package simplebeans.loginbeans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Hp on 11/16/2016.
 */
public class LoginResponseBean {
    @JsonProperty("userId")
    private int userId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("branchId")
    private int branchId;
    @JsonProperty("branchName")
    private String branchName;
    @JsonProperty("permission")
    private String permission;
    @JsonProperty("shiftId")
    private String shiftId;

    public LoginResponseBean(int userId, String name, int branchId, String branchName, String permission, String shiftId) {
        this.setUserId(userId);
        this.setName(name);
        this.setBranchId(branchId);
        this.setBranchName(branchName);
        this.setPermission(permission);
        this.setShiftId(shiftId);
    }

    public LoginResponseBean() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }
}
