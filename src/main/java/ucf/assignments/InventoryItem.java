package ucf.assignments;

import java.math.BigDecimal;

public class InventoryItem {
    private BigDecimal value;
    private String serialNumber;
    private String name;


    public InventoryItem(BigDecimal v, String s, String n){
        this.setValue(v);
        this.setSerialNumber(s);
        this.setName(n);
    }


    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal s) {
        value = s;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String s) {
        serialNumber = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String s) {
        name = s;
    }
}
