package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class AppController {

    public TableColumn itemValueColumn;
    public TableColumn itemSerialNumberColumn;
    public TableColumn itemNameColumn;
    public TextField itemValueTextField;
    public TextField itemSerialNumberTextField;
    public TextField itemNameTextField;
    public TextField searchTextField;

    @FXML
    public void addInventoryItemButtonClicked(ActionEvent actionEvent) {
        addInventoryItem();
    }

    @FXML
    public void saveAsButtonClicked(ActionEvent actionEvent) {
        saveInventoryList();
    }

    @FXML
    public void openButtonClicked(ActionEvent actionEvent) {
        openInventoryList();
    }

    @FXML
    public void deleteSelectedInventoryItemButtonClicked(ActionEvent actionEvent) {
        deleteInventoryItem();
    }

    public void addInventoryItem() {
        // create a new Item for the InventoryItems
        // get the current text from the text boxes and scan them to temp variables
        // determine if name is a valid length
        // determine if the serial number is a collection of 10 digits/numbers
        // if so, add the information to the InventoryList/TableView
    }

    public void saveInventoryList() {
        // get the file extension of the file they want to save it to
        // if(an html file) createHTMLFile(String filename)
        // if(a tsv file) createTSVFile(String filename)
    }

    public void createTSVFile(String fileName){
        // open a file with the filename.txt in the location chosen by the user.
        // for(InventoryList.size) output the value, serial number and name separated by tabs
        // close the file
    }

    public void createHTMLFile(String fileName){
        // figure out how to do html files lol
    }

    public void openInventoryList(){
        // while(!EOF)
        // scan in things to temp variables (depending on if the file is TSV/HTML)
        // add them to InventoryList/TableView
    }

    public void deleteInventoryItem(){
        // get selected item
        // remove it from the InventoryList/TableView
    }
}
