package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InventoryList {
    ObservableList<InventoryItem> InventoryList;

    public InventoryList(){
        this.InventoryList = FXCollections.observableArrayList();
    }
}
