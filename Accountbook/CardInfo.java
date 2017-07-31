package Accountbook;

import com.sun.deploy.security.MozillaJSSDSASignature;

public class CardInfo {
    private String cardcheck;
    private int moneyLocation;
    private int usageLocationS;
    private int usageLocationF;
    private int code;

    CardInfo () {
        this("",0,0,0,0);
    }

    CardInfo (String cartcheck, int moneyLocation, int usageLocationS, int usageLocationF, int code){
        this.cardcheck = cardcheck;
        this.moneyLocation = moneyLocation;
        this.usageLocationS = usageLocationS;
        this.usageLocationF = usageLocationF;
        this.code = code;

    }

    public void setCardcheck(String cardcheck) {
        this.cardcheck = cardcheck;
    }

    public void setMoneyLocation(int moneyLocation) {
        this.moneyLocation = moneyLocation;
    }

    public void setUsageLocationF(int usageLocationF) {
        this.usageLocationF = usageLocationF;
    }

    public void setUsageLocationS(int usageLocationS) {
        this.usageLocationS = usageLocationS;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getMoneyLocation() {
        return moneyLocation;
    }

    public int getUsageLocationF() {
        return usageLocationF;
    }

    public int getUsageLocationS() {
        return usageLocationS;
    }

    public String getCardcheck() {
        return cardcheck;
    }

    public int getCode() {
        return code;
    }
}
