package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

public class AppController{

    @FXML
    public TableColumn<InventoryItem, String> itemValueColumn;
    @FXML
    public TableColumn<InventoryItem, String> itemSerialNumberColumn;
    @FXML
    public TableColumn<InventoryItem, String> itemNameColumn;
    @FXML
    public TextField itemValueTextField;
    @FXML
    public TextField itemSerialNumberTextField;
    @FXML
    public TextField itemNameTextField;
    @FXML
    public TextField searchTextField;
    @FXML
    public TableView<InventoryItem> myTable;

    public InventoryList myList = new InventoryList();


    /*@Override
    public void initialize(URL location, ResourceBundle resources) {
        FilteredList<InventoryItem> filteredList = new FilteredList<>(myList.InventoryList, b -> true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(InventoryItem -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(String.valueOf(InventoryItem.getValue()).contains(lowerCaseFilter)){
                    return true;
                }
                else if(InventoryItem.getSerialNumber().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if(InventoryItem.getName().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else{
                    return false;
                }
            });
        });

        SortedList<InventoryItem> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(myTable.comparatorProperty());
        myTable.setItems(sortedList);
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
        deleteInventoryItem(myTable.getSelectionModel().getSelectedIndex());
    }

    @FXML
    public void changeItemValue(TableColumn.CellEditEvent cellEditEvent) {
        InventoryItem selected = myTable.getSelectionModel().getSelectedItem();
        selected.setValue(BigDecimal.valueOf((double)cellEditEvent.getNewValue()));
    }

    @FXML
    public void changeItemSerialNumber(TableColumn.CellEditEvent cellEditEvent) {
        InventoryItem selected = myTable.getSelectionModel().getSelectedItem();
        selected.setSerialNumber(cellEditEvent.getNewValue().toString());
    }

    @FXML
    public void changeItemName(TableColumn.CellEditEvent cellEditEvent) {
        InventoryItem selected = myTable.getSelectionModel().getSelectedItem();
        selected.setName(cellEditEvent.getNewValue().toString());

    }

    @FXML
    public void searchButtonClicked(ActionEvent actionEvent) {
        myTable.setItems(searchInventoryItems(searchTextField.getText()));
    }

    public void addInventoryItem() {
        // create a new Item for the InventoryItems
        // get the current text from the text boxes and scan them to temp variables
        if(!isValueANumber(itemValueTextField.getText())){
            errorPopUp();
        }
        else{
            BigDecimal tempValue = new BigDecimal(itemValueTextField.getText());
            String tempSerialNumber = itemSerialNumberTextField.getText().toUpperCase();
            String tempName = itemNameTextField.getText();
            // determine if name is a valid length
            // determine if the serial number is a collection of 10 digits/numbers
            if(!isNameValid(tempName) || !isSerialNumberValid(tempSerialNumber)){
                errorPopUp();
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
        if(name == null || name.isEmpty()){
            return false;
        }
        // check to see that string contains only digits
        for(int i = 0; i < name.length(); i++){
            if((!Character.isDigit(name.charAt(i))) && (name.charAt(i) != '.')){
                return false;
            }
        }
        return true;
    }

    public boolean isSerialNumberValid(String name) {
        if(name == null || name.isEmpty()){
            return false;
        }
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
            if(myList.InventoryList.get(i).getSerialNumber().equals(name)){
                return false;
            }
        }
        return true;
    }

    public boolean isNameValid(String name) {
        if(name == null || name.isEmpty()){
            return false;
        }
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

    public void deleteInventoryItem(int i){
        // remove item of index i from the InventoryList/TableView
        myList.InventoryList.remove(i);
    }

    public ObservableList<InventoryItem> searchInventoryItems(String text) {
        if(text == null || text.isEmpty()){
            return myList.InventoryList;
        }
        ObservableList<InventoryItem> searchedList = FXCollections.observableArrayList();
        for(int i = 0; i < myList.InventoryList.size(); i++){
            if(myList.InventoryList.get(i).getValue().toString().contains(text) || myList.InventoryList.get(i).getSerialNumber().contains(text) || myList.InventoryList.get(i).getName().contains(text)){
                searchedList.add(myList.InventoryList.get(i));
            }
        }
        return searchedList;
    }

    public void errorPopUp() {
        try {
            Stage error = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ErrorMessage.fxml")));
            Scene scene = new Scene(root);

            error.setScene(scene);
            error.setTitle("App");
            error.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
