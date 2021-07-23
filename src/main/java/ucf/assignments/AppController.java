package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;

public class AppController {

    public TableColumn itemValueColumn;
    public TableColumn itemSerialNumberColumn;
    public TableColumn itemNameColumn;
    public TextField itemValueTextField;
    public TextField itemSerialNumberTextField;
    public TextField itemNameTextField;
    public TextField searchTextField;
    public InventoryList myList = new InventoryList();
    public TableView myTable;


    /*@Override
    public void initialize(URL location, ResourceBundle resources) {
        itemValueColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("value"));
        itemSerialNumberColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("serialNumber"));
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("name"));
        myTable.setEditable(true);
        itemValueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        itemSerialNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        itemNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }
     */

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

    @FXML
    public void changeItemValue(TableColumn.CellEditEvent cellEditEvent) {
        InventoryItem selected = (InventoryItem) myTable.getSelectionModel().getSelectedItem();
        selected.setValue(BigDecimal.valueOf((double)cellEditEvent.getNewValue()));

    }

    @FXML
    public void changeItemSerialNumber(TableColumn.CellEditEvent cellEditEvent) {
        InventoryItem selected = (InventoryItem) myTable.getSelectionModel().getSelectedItem();
        selected.setSerialNumber(cellEditEvent.getNewValue().toString());
    }

    @FXML
    public void changeItemName(TableColumn.CellEditEvent cellEditEvent) {
        InventoryItem selected = (InventoryItem) myTable.getSelectionModel().getSelectedItem();
        selected.setName(cellEditEvent.getNewValue().toString());

    }

    public void addInventoryItem() {
        // create a new Item for the InventoryItems
        // get the current text from the text boxes and scan them to temp variables
        if(!isValueANumber(itemValueTextField.getText())){
            // error box
        }
        else{
            BigDecimal tempValue = new BigDecimal(itemValueTextField.getText());
            String tempSerialNumber = itemSerialNumberTextField.getText().toUpperCase();
            String tempName = itemNameTextField.getText();
            // determine if name is a valid length
            // determine if the serial number is a collection of 10 digits/numbers
            if(!isNameValid(tempName) || !isSerialNumberValid(tempSerialNumber)){
                // error box
            }
            // if so, add the information to the InventoryList/TableView
            else {
                myList.InventoryList.add(new InventoryItem(tempValue, tempSerialNumber, tempName));
                itemValueColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("value"));
                itemSerialNumberColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("serialNumber"));
                itemNameColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("name"));
                myTable.setItems(myList.InventoryList);
                // clear the text fields
                itemValueTextField.clear();
                itemSerialNumberTextField.clear();
                itemNameTextField.clear();
            }
        }
    }

    public boolean isValueANumber(String name) {
        // check to see that string contains only digits
        for(int i = 0; i < name.length(); i++){
            if((!Character.isDigit(name.charAt(i))) && (name.charAt(i) != '.')){
                return false;
            }
        }
        return true;
    }

    public boolean isSerialNumberValid(String name) {
        // check to see if the serial number is the valid length of 10
        if(name.length() != 10){
            return false;
        }
        // check to see if the serial number contains only letters or numbers
        for(int i = 0; i < name.length(); i++){
            if(!Character.isLetterOrDigit(name.charAt(i))){
                return false;
            }
        }
        // check to see if it matches any other serial number
        for(int i = 0; i < myList.InventoryList.size(); i++){
            if(myList.InventoryList.get(i).serialNumber.equals(name)){
                return false;
            }
        }
        return true;
    }

    public boolean isNameValid(String name) {
        // check to see that the name is a valid length
        return (name.length() >= 2) && (name.length() <= 256);
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
