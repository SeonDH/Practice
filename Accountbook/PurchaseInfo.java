package Accountbook;

public class PurchaseInfo {
    private Code code;
    private String usage;
    private int amountOfPayment;

    PurchaseInfo(Code code, String usage, int amountOfPayment) {
        this.code = code;
        this.usage = usage;
        this.amountOfPayment = amountOfPayment;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public Code getCode() {
        return code;
    }

    public void setAmountOfPayment(int amountOfPayment) {
        this.amountOfPayment = amountOfPayment;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public int getAmountOfPayment() {
        return amountOfPayment;
    }

    public String getUsage() {
        return usage;
    }
}
