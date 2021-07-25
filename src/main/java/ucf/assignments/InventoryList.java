/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Edmund Johnson V
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InventoryList {
    ObservableList<InventoryItem> InventoryList;

    public InventoryList(){
        this.InventoryList = FXCollections.observableArrayList();
    }
}
