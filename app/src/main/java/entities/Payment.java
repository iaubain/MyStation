package entities;

import com.orm.SugarRecord;

/**
 * Created by Hp on 11/15/2016.
 */
public class Payment extends SugarRecord {
    private int identification;
    private String name;
    private String type;
    private boolean hasExtra;
    private String extraType;//numberPlate:textCaps; msisdn:phone; cardNumber:numeric; voucher:numeric;
    private boolean needExternal;
    private String externalType;//telephony; voucher; bank; direct

    public Payment(int identification, String name, String type, boolean hasExtra, String extraType, boolean needExternal, String externalType) {
        this.setIdentification(identification);
        this.setName(name);
        this.setType(type);
        this.setHasExtra(hasExtra);
        this.setExtraType(extraType);
        this.setNeedExternal(needExternal);
        this.setExternalType(externalType);
    }

    public Payment() {

    }

    public int getIdentification() {
        return identification;
    }

    public void setIdentification(int identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHasExtra() {
        return hasExtra;
    }

    public void setHasExtra(boolean hasExtra) {
        this.hasExtra = hasExtra;
    }

    public String getExtraType() {
        return extraType;
    }

    public void setExtraType(String extraType) {
        this.extraType = extraType;
    }

    public boolean isNeedExternal() {
        return needExternal;
    }

    public void setNeedExternal(boolean needExternal) {
        this.needExternal = needExternal;
    }

    public String getExternalType() {
        return externalType;
    }

    public void setExternalType(String externalType) {
        this.externalType = externalType;
    }
}
