/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Edmund Johnson V
 */
package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(itemValueColumn != null){
            itemValueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        }
        if(itemSerialNumberColumn != null){
            itemSerialNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        }
        if(itemNameColumn != null){
            itemNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        }
    }

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



    @FXML
    public void addInventoryItemButtonClicked(ActionEvent actionEvent) {
        addInventoryItem();
    }

    @FXML
    public void saveAsButtonClicked(ActionEvent actionEvent) {
        FileChooser saveFileChooser = new FileChooser();
        saveFileChooser.setTitle("Save File");
        try {
            Stage chooser = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FileChooser.fxml")));
            Scene scene = new Scene(root);

            File file = saveFileChooser.showSaveDialog(chooser);

            PrintWriter output = new PrintWriter(file);

            if(file.getName().endsWith(".txt")){
                output.print("Value");
                for(int i = 0; i < myList.InventoryList.get(biggestValueFinder()).getValue().length()/4; i++){
                    output.print("\t");
                }
                output.println("Serial Number\tName");
                for(int i = 0; i < myList.InventoryList.size(); i++){
                    output.println(myList.InventoryList.get(i).getValue() + "\t" + myList.InventoryList.get(i).getSerialNumber() + "\t\t" + myList.InventoryList.get(i).getName());
                }
                output.close();
            }

            else if(file.getName().endsWith(".html")){
                output.print("<!DOCTYPE html>\n<html>\n<body><style>\nth {\n" +
                        "  text-align: left;\n" +
                        "}\n" +
                        "</style>\n" +
                        "<table style=\"width:100%\">\n" +
                        "  <tr>\n" +
                        "    <th>Value</th>\n" +
                        "    <th>Serial Number</th>\n" +
                        "    <th>Name</th>\n" +
                        "  </tr>\n" +
                        "  <tr>");
                for(int i = 0; i < myList.InventoryList.size(); i++){
                    output.println("<tr>\n" +
                            "    <td>" + myList.InventoryList.get(i).getValue() +  "</td>\n" +
                            "    <td>" + myList.InventoryList.get(i).getSerialNumber() +  "</td>\n" +
                            "    <td>" + myList.InventoryList.get(i).getName() +  "</td>\n" +
                            "  </tr>\n</table>\n</body>\n</html>");
                }
                output.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        if(!isValueANumber(cellEditEvent.getNewValue().toString())){
            errorPopUp();
        }
        else{
            int i = myTable.getSelectionModel().getSelectedIndex();
            myList.InventoryList.get(i).setValue(cellEditEvent.getNewValue().toString());
            myTable.setItems(myList.InventoryList);
        }
    }

    @FXML
    public void changeItemSerialNumber(TableColumn.CellEditEvent cellEditEvent) {
        InventoryItem selected = myTable.getSelectionModel().getSelectedItem();
        if(!isSerialNumberValid(cellEditEvent.getNewValue().toString().toUpperCase())){
            errorPopUp();
        }
        else{
            int i = myTable.getSelectionModel().getSelectedIndex();
            myList.InventoryList.get(i).setSerialNumber(cellEditEvent.getNewValue().toString().toUpperCase());
            myTable.setItems(myList.InventoryList);
        }
    }

    @FXML
    public void changeItemName(TableColumn.CellEditEvent cellEditEvent) {
        InventoryItem selected = myTable.getSelectionModel().getSelectedItem();
        if(!isNameValid(cellEditEvent.getNewValue().toString())){
            errorPopUp();
        }
        else{
            int i = myTable.getSelectionModel().getSelectedIndex();
            myList.InventoryList.get(i).setName(cellEditEvent.getNewValue().toString());
            myTable.setItems(myList.InventoryList);
        }
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
            String tempValue = itemValueTextField.getText();
            if(!tempValue.contains(".")){
                tempValue = tempValue.concat(".00");
            }
            if(!tempValue.contains("$")){
                tempValue = "$" + tempValue;
            }
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
        int j = 0;
        // null/empty case
        if(name == null || name.isEmpty()){
            return false;
        }
        // check to see that string contains only digits
        for(int i = 0; i < name.length(); i++){
            if((!Character.isDigit(name.charAt(i))) && (name.charAt(i) != '.')){
                return false;
            }
            if(name.charAt(i) == '.'){
                if(j == 1){
                    return false;
                }

                else{
                    j = 1;
                }
            }
        }
        return true;
    }

    public boolean isSerialNumberValid(String name) {
        // null/empty case
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
        // null/empty case
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
            if(String.valueOf(myList.InventoryList.get(i).getValue()).contains(text) || myList.InventoryList.get(i).getSerialNumber().contains(text) || myList.InventoryList.get(i).getName().contains(text)){
                searchedList.add(new InventoryItem(myList.InventoryList.get(i).getValue(), myList.InventoryList.get(i).getSerialNumber(), myList.InventoryList.get(i).getName()));
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

    public int biggestValueFinder(){
        int largest = 0;
        for(int i = 0; i < myList.InventoryList.size(); i++){
            if(myList.InventoryList.get(i).getValue().length() > myList.InventoryList.get(largest).getValue().length()){
                largest = i;
            }
        }
        return largest;
    }
}
