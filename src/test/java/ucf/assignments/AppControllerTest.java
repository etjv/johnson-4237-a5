package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppControllerTest {

    @Test
    void addInventoryItem() {
        // create an expected InventoryList
        InventoryList expected = new InventoryList();
        expected.InventoryList.add(new InventoryItem("600.00", "cerealnumb", "the name"));
        // create an empty actual InventoryList
        InventoryList actual = new InventoryList();
        // use addInventoryItem to add a the expected attributes to it
        addInventoryItem();
        // assert the lists are equal
        assertEquals(expected, actual);
    }

    @Test
    void openInventoryList() {
        // figure out how to test this
    }

    @Test
    void deleteInventoryItem() {
        // create an empty expected InventoryList
        InventoryList expected = new InventoryList();
        // create an actual InventoryList with an item in it
        InventoryList actual = new InventoryList();
        actual.InventoryList.add(new InventoryItem("600.00", "cerealnumb", "the name"));
        // use deleteInventoryItem to remove the item from the actual
        deleteInventoryItem();
        // assert the lists are equal
        assertEquals(expected, actual);
    }

    @Test
    void isValueANumber() {
        AppController app = new AppController();
        boolean actual = app.isValueANumber("600");
        assertTrue(actual);

    }

    @Test
    void isSerialNumberValid() {
        AppController app = new AppController();
        boolean actual = app.isSerialNumberValid("XXXXXXXXXX");
        assertTrue(actual);
    }

    @Test
    void isNameValid() {
        AppController app = new AppController();
        boolean actual = app.isNameValid("name");
        assertTrue(actual);
    }

    @Test
    void biggestValueFinder() {
        AppController app = new AppController();
        InventoryList list = new InventoryList();
        list.InventoryList.add(new InventoryItem("6", "", ""));
        list.InventoryList.add(new InventoryItem("60", "", ""));
        list.InventoryList.add(new InventoryItem("600", "", ""));

        int expected = 2;
        int actual = app.biggestValueFinder();
        assertEquals(expected, actual);
    }
}