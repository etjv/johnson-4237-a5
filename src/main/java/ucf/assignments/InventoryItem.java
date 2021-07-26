/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Edmund Johnson V
 */

package ucf.assignments;

public class InventoryItem {
    private String value;
    private String serialNumber;
    private String name;


    public InventoryItem(String v, String s, String n){
        this.setValue(v);
        this.setSerialNumber(s);
        this.setName(n);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String s) {
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
