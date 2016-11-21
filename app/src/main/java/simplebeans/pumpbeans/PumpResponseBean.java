package simplebeans.pumpbeans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Hp on 11/18/2016.
 */
public class PumpResponseBean {
    @JsonProperty("pumpId")
    private int pumpId;
    @JsonProperty("pumpName")
    private String pumpName;
    @JsonProperty("branchId")
    private int branchId;
    @JsonProperty("status")
    private int status;
    @JsonProperty("nozzleList")
    private List<NozzleBean> nozzles;

    public PumpResponseBean(int pumpId, String pumpName, int branchId, int status, List<NozzleBean> nozzles) {
        this.setPumpId(pumpId);
        this.setPumpName(pumpName);
        this.setBranchId(branchId);
        this.setStatus(status);
        this.setNozzles(nozzles);
    }

    public PumpResponseBean() {

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

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<NozzleBean> getNozzles() {
        return nozzles;
    }

    public void setNozzles(List<NozzleBean> nozzles) {
        this.nozzles = nozzles;
    }
}
