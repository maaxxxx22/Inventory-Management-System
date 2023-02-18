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

/** A class for the add product controller is created for the add part scene. */

public class AddProductForm implements Initializable {
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
    private TableColumn<PartDTO, Integer> aInventoryLevel;
    @FXML
    private TableColumn<PartDTO, Double> aPriceCost;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField stock;
    @FXML
    private TextField price;
    @FXML
    private TextField max;
    @FXML
    private TextField min;

    @FXML
    private TextField partSearch;

    ObservableList<PartDTO> associatePart = FXCollections.observableArrayList();
    ObservableList<ProductDT0> associateProducts = FXCollections.observableArrayList();


    /** Initializes the add product scene. */
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
        aInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }

    public void sendPart(ProductDT0 part) {
        id.setText(String.valueOf(part.getId()));
        name.setText(String.valueOf(part.getName()));
        stock.setText(String.valueOf(part.getStock()));
        price.setText(String.valueOf(part.getPrice()));
        max.setText(String.valueOf(part.getMax()));
        min.setText(String.valueOf(part.getMin()));

    }

    public void onMax(KeyEvent keyEvent) throws Exception {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning alert");

        alert.setHeaderText("Max and Min Values");
        alert.setContentText("The Min value must be less than the Max and the Inventory level must be between those Values");

        alert.showAndWait();
    }

    /** This method is responsible for adding the selected part. */
    public void onAdd(ActionEvent actionEvent) throws Exception {
        PartDTO part = partTableView.getSelectionModel().getSelectedItem();
        associatePart.add(part);
        selectedPartTableView.setItems(associatePart);
    }

    /** This function deletes a part from the associated parts list. */
    public void deleteAssociatedPart(ActionEvent actionEvent) throws Exception {
        if (selectedPartTableView.getSelectionModel().isEmpty()) {

            System.out.println("Please choose a product from above");
            return;
        } else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning alert");

            alert.setHeaderText("ALERT");
            alert.setContentText("Are you sure you want to delete the Part?");

            alert.showAndWait();

        }
        {
            int Product = selectedPartTableView.getSelectionModel().getSelectedIndex();
            associatePart.remove(Product);
            selectedPartTableView.setItems(associatePart);
        }

    }

    /** This method is checks if the value inserted into the fields is valid, and if so saves the new product. */
    public void onSave(ActionEvent actionEvent) throws Exception {
        if(validate()) {
            try {
                ProductDT0 product = new ProductDT0(Inventory.getAllProducts().size() + 1,
                        name.getText(), Double.parseDouble(price.getText()), Integer.parseInt(stock.getText()),
                        Integer.parseInt(min.getText()), Integer.parseInt(max.getText()));

                for (ProductDT0 p : associateProducts) {
                    product.addAssociatedProduct(p);
                }

                for (PartDTO p : associatePart) {
                    product.addAssociatedPart(p);
                }

                Inventory.addProduct(product);
                System.out.println("ProductDT0 was added!!");

                Parent addProduct = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
                Scene scene = new Scene(addProduct);

                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }
            catch (NumberFormatException e) {
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");

                    alert.setHeaderText("ERROR!!");
                    alert.setContentText("All text fields must be fill in and have the correct data type");

                    alert.showAndWait();

                }
            }
        }
    }

    /** This method validates the entries. */
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

    /** This method cancels any changes that are made when the cancel button is pressed. */
    public void onCancel(ActionEvent actionEvent) throws Exception {
        Parent addProduct = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Scene scene = new Scene(addProduct);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /** This method is responsible for searching the list of parts for the part id or name. */
    @FXML
    public void onSearch(KeyEvent keyEvent) throws IOException {
        try {

            Integer Id = Integer.valueOf(partSearch.getText());
            ObservableList<PartDTO> items = FXCollections.observableArrayList();
            ObservableList<PartDTO> tableview_List = Inventory.getAllParts();
            for (PartDTO P : tableview_List) {
                if (P.getId() == Id )
                    items.add(P);
            }
            partTableView.setItems(items);
        }
        catch (NumberFormatException e){
            String partialName = partSearch.getText();
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