package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

class InventoryItemTest {

    @Test
    void getValue() {
        // create an expected string
        String expected = "600";
        // create actual inventory item
        InventoryItem actual = new InventoryItem("600", "XXXXXXXXXX", "name");
        // assert equals
        assertEquals(expected, actual.getValue());
    }

    @Test
    void setValue() {
        // create an expected string
        String expected = "600";
        // create an actual inventory item with blank variables
        InventoryItem actual = new InventoryItem("", "", "");
        // call setValue
        actual.setValue("600");
        // assert equals
        assertEquals(expected, actual.getValue());
    }

    @Test
    void getSerialNumber() {
        // create an expected string
        String expected = "XXXXXXXXXX";
        // create actual inventory item
        InventoryItem actual = new InventoryItem("600", "XXXXXXXXXX", "name");
        // assert equals
        assertEquals(expected, actual.getSerialNumber());
    }

    @Test
    void setSerialNumber() {
        // create an expected string
        String expected = "XXXXXXXXXX";
        // create an actual inventory item with blank variables
        InventoryItem actual = new InventoryItem("", "", "");
        // call setValue
        actual.setSerialNumber("XXXXXXXXXX");
        // assert equals
        assertEquals(expected, actual.getSerialNumber());
    }

    @Test
    void getName() {
        // create an expected string
        String expected = "name";
        // create actual inventory item
        InventoryItem actual = new InventoryItem("600", "XXXXXXXXXX", "name");
        // assert equals
        assertEquals(expected, actual.getName());
    }

    @Test
    void setName() {
        // create an expected string
        String expected = "name";
        // create an actual inventory item with blank variables
        InventoryItem actual = new InventoryItem("", "", "");
        // call setValue
        actual.setName("name");
        // assert equals
        assertEquals(expected, actual.getName());
    }
}