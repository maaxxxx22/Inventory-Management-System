package com.org;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
//import com.example.first_javafx_project.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Okunta Braide
 */

/** This class is the modify-product controller for the modify-product Scene */

public class ModifyProductForm implements Initializable {

    @FXML
    private TableView<PartDTO> partTableView;
    @FXML
    public TableColumn<PartDTO, Integer> partID;
    @FXML
    private TableColumn<PartDTO, String> partName;
    @FXML
    private TableColumn<PartDTO, Integer> inventoryLevel;
    @FXML
    private TableColumn<PartDTO, Double> priceCost;
    @FXML
    private TableView<PartDTO> selectedPartTableView;
    @FXML
    public TableColumn<PartDTO, Integer> aPartID;
    @FXML
    private TableColumn<PartDTO, String> aPartName;
    @FXML
    private TableColumn<PartDTO, Integer> aInventory;
    @FXML
    private TableColumn<PartDTO, Double> aPriceCost;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField stock;
    @FXML
    private TextField priceText;
    @FXML
    private TextField max;
    @FXML
    private TextField min;
    @FXML
    private TextField addProductSearch;

    ObservableList<PartDTO> associatePart = FXCollections.observableArrayList();

    /** Initializes the Add Product Scene. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partTableView.setItems(Inventory.getAllParts());
        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCost.setCellValueFactory(new PropertyValueFactory<>("price"));
        inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));

        aPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        aPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        aPriceCost.setCellValueFactory(new PropertyValueFactory<>("price"));
        aInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }

    /** This method sets each modified property in the selected product.
     *
     * One of the logical errors I ran into was when 'sendProduct' was called, even though the method was able to set
     * the properties to the correct part, the changes did not register. After several debugging sessions on the function,
     * I recognized that in 'selectedPartTableView', I needed to add 'associatePart' to the 'setItems' method before it
     * registered properly.
     *
     * A future enhancement or feature that can further extend the functionality of this program would be to have a
     * database that automatically updates the product and part anytime there is a change in price, name, stock, e.t.c.
     * */
    public void sendProduct(ProductDT0 product) {
        id.setText(String.valueOf(product.getId()));
        name.setText(String.valueOf(product.getName()));
        stock.setText(String.valueOf(product.getStock()));
        priceText.setText(String.valueOf(product.getPrice()));
        max.setText(String.valueOf(product.getMax()));
        min.setText(String.valueOf(product.getMin()));
        associatePart = product.getAllAssociatedParts();
        selectedPartTableView.setItems(associatePart);
    }

    /** This method throws exception if values are not within excepted range. */
    public void onMax(KeyEvent keyEvent) throws Exception {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning alert");

        alert.setHeaderText("Max and Min Values");
        alert.setContentText("The Min value must be less than the Max and the Inventory level must be between those Values");

        alert.showAndWait();
    }

    /** This method executes when a selected part from the parts list is added to product list. */
    @FXML
    public void onAdd(ActionEvent actionEvent) throws Exception {
        PartDTO part = partTableView.getSelectionModel().getSelectedItem();
        associatePart.add(part);
        selectedPartTableView.setItems(associatePart);
    }

    /** This method checks validity of values in the field, and if valid, Product will be added to Inventory. */
    @FXML
    public void onSave(ActionEvent actionEvent) throws Exception {
        if(validate()) {
            try {
                ProductDT0 product = new ProductDT0(Integer.parseInt(id.getText()),
                        name.getText(), Double.parseDouble(priceText.getText()), Integer.parseInt(stock.getText()),
                        Integer.parseInt(min.getText()), Integer.parseInt(max.getText()));
                product.setAllAssociatedParts(associatePart);

                Inventory.updateProduct(product);
                System.out.println("ProductDT0 was Updated!!");

                Parent addParts = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
                Scene scene = new Scene(addParts);

                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();



            }
            catch(NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning alert");

                alert.setContentText("All text fields must have the correct data type");

                alert.showAndWait();
            }
        }

    }

    /** This method validates the entries and makes sure it's within range. */
    public boolean validate() {
        if (Integer.parseInt(min.getText()) >= Integer.parseInt(max.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning alert");
            alert.setHeaderText("Max and Min Values");
            alert.setContentText("The Min value must be less than the Max Value");
            alert.showAndWait();
            return false;
        }
        if (Integer.parseInt(min.getText()) > Integer.parseInt(stock.getText()) || Integer.parseInt(stock.getText()) > Integer.parseInt(max.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning alert");
            alert.setHeaderText("Inv Value");
            alert.setContentText("The Inv value must be between Min and Max Value");
            alert.showAndWait();
            return false;
        }
        if (associatePart.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning alert");
            alert.setHeaderText("PartDTO");
            alert.setContentText("Please select a PartDTO");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    /** This method cancels any changes done in the add product scene when the cancel button is pressed. */
    @FXML
    public void onCancel(ActionEvent actionEvent) throws Exception {
        Parent addParts = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Scene scene = new Scene(addParts);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /** This method is responsible for removing the product entry. */
    @FXML
    public void onRemovePart(ActionEvent actionEvent) throws Exception {
        if (selectedPartTableView.getSelectionModel().isEmpty()) {

            System.out.println("Please choose a product from above");
            return;
        } else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning alert");

            alert.setContentText("Are you sure you want to delete the Part?");

            alert.showAndWait();

        }
        {
            int Product = selectedPartTableView.getSelectionModel().getSelectedIndex();
            associatePart.remove(Product);
            selectedPartTableView.setItems(associatePart);
        }
    }

    /** This method wil search the list of parts by name or part id entered into the field. */
    @FXML
    public void onSearch(KeyEvent keyEvent) throws IOException {
        try {

            Integer Id = Integer.valueOf(addProductSearch.getText());
            ObservableList<PartDTO> items = FXCollections.observableArrayList();
            ObservableList<PartDTO> tableview_List = Inventory.getAllParts();
            for (PartDTO P : tableview_List) {
                if (P.getId() == Id )
                    items.add(P);
            }
            partTableView.setItems(items);
        }
        catch (NumberFormatException e){
            String partialName = addProductSearch.getText();
            ObservableList<PartDTO> items = FXCollections.observableArrayList();
            ObservableList<PartDTO> tableview_List = Inventory.getAllParts();
            for (PartDTO P : tableview_List) {
                if (P.getName().contains(partialName) )
                    items.add(P);
            }
            partTableView.setItems(items);
        }
    }
}
