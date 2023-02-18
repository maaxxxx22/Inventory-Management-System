package com.org;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


/**
 *@author Okunta Braide
 */

/** This class creates the add part controller for the add part scene. */

public class AddPartForm implements Initializable {
    @FXML private Label label;
    @FXML
    private RadioButton inHouseRadio;
    @FXML
    private RadioButton outSourcedRadio;
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
    private TextField machineID;

    /** Initializes the add part scene. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /** This method will enable the company name and field for outsourced parts. */
    @FXML
    public void onInHouse(ActionEvent actionEvent) {
        if (inHouseRadio.isSelected()) {
            this.label.setText("Machine ID");
        }
    }

    /** This method will disable company name and label when outsource button is pressed. */
    @FXML
    public void onOutSourced(ActionEvent actionEvent) {
        if (outSourcedRadio.isSelected()) {
            this.label.setText("Company Name");
        }
    }

    /** This method is responsible for adding or updating data. */
    @FXML
    public void onSave(ActionEvent actionEvent) throws Exception {
        if(validate()) {
            try {
                if (inHouseRadio.isSelected()) {
                    PartDTO inHouse = new InHousePartDTO(Inventory.getAllParts().size() + 1,
                            name.getText(), Double.parseDouble(price.getText()), Integer.parseInt(stock.getText()),
                            Integer.parseInt(min.getText()), Integer.parseInt(max.getText()), Integer.parseInt(machineID.getText()));

                    Inventory.addPart(inHouse);
                    System.out.println("PartDTO was added!!");
                }
                if (outSourcedRadio.isSelected()) {
                    PartDTO outSourced = new OutSourcedPartDTO(Inventory.getAllParts().size() + 1,
                            name.getText(), Double.parseDouble(price.getText()), Integer.parseInt(stock.getText()),
                            Integer.parseInt(min.getText()), Integer.parseInt(max.getText()), machineID.getText());

                    Inventory.addPart(outSourced);
                    System.out.println("PartDTO was added!!");
                }
                Parent addParts = FXMLLoader.load(MainApplication.class.getResource("MainForm.fxml"));
                Scene scene = new Scene(addParts);

                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }
            catch(NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning alert");

                alert.setHeaderText("INCORRECT DATA TYPE");
                alert.setContentText("All text fields must be fill in and have the correct data type");

                alert.showAndWait();
            }
        }

    }

    /**  This method will return false if condition passes, otherwise will return true. */
    public boolean validate() {
        if (!inHouseRadio.isSelected() && !outSourcedRadio.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning alert");
            alert.setContentText("Please select In House or OutSourcedPartDTO Radio Button");
            alert.showAndWait();
            return false;
        }
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
        return true;
    }

    /** This method is responsible for a cancel or close action. */
    @FXML
    public void onCancel(ActionEvent actionEvent) throws Exception {
        Parent addParts = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Scene scene = new Scene(addParts);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
