package entities;

import com.orm.SugarRecord;

/**
 * Created by Hp on 11/15/2016.
 */
public class MyDevice extends SugarRecord {
    private String identification;
    private String name;
    private String serialNumber;
    private String mainMsisdn;
    private String auxMsisdn;

    public MyDevice(String identification, String name, String serialNumber, String mainMsisdn, String auxMsisdn) {
        this.setIdentification(identification);
        this.setName(name);
        this.setSerialNumber(serialNumber);
        this.setMainMsisdn(mainMsisdn);
        this.setAuxMsisdn(auxMsisdn);
    }

    public MyDevice() {

    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMainMsisdn() {
        return mainMsisdn;
    }

    public void setMainMsisdn(String mainMsisdn) {
        this.mainMsisdn = mainMsisdn;
    }

    public String getAuxMsisdn() {
        return auxMsisdn;
    }

    public void setAuxMsisdn(String auxMsisdn) {
        this.auxMsisdn = auxMsisdn;
    }
}
