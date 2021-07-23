package ucf.assignments;

import java.math.BigDecimal;

public class InventoryItem {
    BigDecimal value;
    String serialNumber;
    String name;

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
