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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Okunta Braide
 *
 * The generated Javadoc can be found in the main folder, here is the file path
 * ("/InventoryManagementSystem/src/main/javadoc").
 */


/** This class creates the main controller for the Main FXML Scene */

public class MainForm implements Initializable {
        @FXML
        private TextField partSearch;
        @FXML
        private TableView<ProductDT0> prodTableview;
        @FXML
        private TableView<PartDTO> partTableview;
        @FXML
        private TextField prodSearch;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            partTableview.setItems(Inventory.getAllParts());
            prodTableview.setItems(Inventory.getAllProducts());
        }

    /** This method is called when the add button on the parts table is pressed. */
    @FXML
    public void onAdd(ActionEvent actionEvent) throws IOException {
        Parent addParts = FXMLLoader.load(getClass().getResource("AddPartForm.fxml"));
        Scene scene = new Scene(addParts);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /** This method is called when the modify button on the parts table is pressed. */
    @FXML
    public void onModify(ActionEvent actionEvent) throws IOException {

        if (!(partTableview.getSelectionModel().getSelectedItem() == null)) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ModifyPartForm.fxml"));

            loader.load();

            ModifyPartForm controller = loader.getController();
            controller.sendPart(partTableview.getSelectionModel().getSelectedItem());
            Parent root = loader.getRoot();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning alert");

            alert.setHeaderText("ALERT");
            alert.setContentText("You must select a part");

            alert.showAndWait();
        }
    }

    /** This method is called when the delete button on the parts table is pressed. */
    @FXML
    public void onDelete(ActionEvent actionEvent) throws IOException {
        if (partTableview.getSelectionModel().isEmpty()) {
                System.out.println("Please choose a part from above");
                return;
           } else
            {
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning alert");
                    alert.setContentText("Are you sure you want to delete the Part?");

                    alert.showAndWait();

                }
               int part = partTableview.getSelectionModel().getSelectedIndex();
                partTableview.getItems().remove(part);
           }
    }

    /** This method is called when the add button on the product table is pressed. */
    @FXML
    public void onProdAdd(ActionEvent actionEvent) throws IOException {
        Parent addParts = FXMLLoader.load(getClass().getResource("AddProductForm.fxml"));
        Scene scene = new Scene(addParts);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();


    }

    /** This method is called when the modify button on the product table is pressed. */
    @FXML
    public void onProdModify(ActionEvent actionEvent) throws IOException {
        if (!(prodTableview.getSelectionModel().getSelectedItem() == null)) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ModifyProductForm.fxml"));

            loader.load();

            ModifyProductForm controller = loader.getController();
            controller.sendProduct(prodTableview.getSelectionModel().getSelectedItem());
            Parent root = loader.getRoot();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning alert");

            alert.setHeaderText("ALERT");
            alert.setContentText("You must select a part");

            alert.showAndWait();
        }
    }

    /** This method is called when the delete button on the product table is pressed. */
    @FXML
    public void onProdDelete(ActionEvent actionEvent) throws IOException {

           if (prodTableview.getSelectionModel().isEmpty()) {
               System.out.println("Please choose a product from above");
               return;
           } else

           {
               Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Warning alert");
               alert.setContentText("Are you sure you want to delete the Product?");

               alert.showAndWait();

        }
        int Product = prodTableview.getSelectionModel().getSelectedIndex();
        prodTableview.getItems().remove(Product);


    }

    /** This method is called when the search button on the parts table is pressed. */
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
            partTableview.setItems(items);
        }
        catch (NumberFormatException e){
                String partialName = partSearch.getText();
                ObservableList<PartDTO> items = FXCollections.observableArrayList();
                ObservableList<PartDTO> tableview_List = Inventory.getAllParts();
                for (PartDTO P : tableview_List) {
                    if (P.getName().contains(partialName) )
                        items.add(P);
                }
                partTableview.setItems(items);
            }
    }

    /** This method is called when the search button on the product table is pressed. */
    @FXML
    public void onProdSearch(KeyEvent keyEvent) throws IOException {
        try {
            Integer Id = Integer.valueOf(prodSearch.getText());
            ObservableList<ProductDT0> items = FXCollections.observableArrayList();
            ObservableList<ProductDT0> tableview_List = Inventory.getAllProducts();
            for (ProductDT0 Pr : tableview_List) {
                if (Pr.getId() == Id ) {
                    items.add(Pr);
                }
            }
            prodTableview.setItems(items);
        }
        catch (NumberFormatException e){
            String partialName = prodSearch.getText();

            ObservableList<ProductDT0> items = FXCollections.observableArrayList();
            ObservableList<ProductDT0> tableview_List = Inventory.getAllProducts();
            for (ProductDT0 Pr : tableview_List) {

                if (Pr.getName().contains(partialName)) {
                    items.add(Pr);
                }
            }
            prodTableview.setItems(items);
        }

    }

    /** This method will exit the program. */
    @FXML
    public void onExit(ActionEvent actionEvent) throws IOException {
        System.out.println("Exit Program");
        System.exit(0);
    }
}




