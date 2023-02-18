package com.org;

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
//import com.example.first_javafx_project.model.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Okunta Braide
 */

/** This class creates modify part controller for the AddPart XML Scene */

public class ModifyPartForm implements Initializable {
    @FXML Button save;
    @FXML Button cancel;
    @FXML
    private RadioButton inHouseRadio;
    @FXML
    private RadioButton outSourcedRadio;
    @FXML
    private ToggleGroup radiobutton;
    @FXML private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField stock;
    @FXML
    private TextField priceCost;
    @FXML
    private TextField max;
    @FXML
    private TextField min;
    @FXML
    private TextField machineID;
    @FXML
    private Label label;
    private TableView<PartDTO> modifyPartTableview;

    /** Initializes the Add Part Scene. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /** This method will change the title for machine label to machine id when radio button is pressed. */
    public void onInHouse(ActionEvent actionEvent) {
        if (inHouseRadio.isSelected()) {
            this.label.setText("Machine ID");
        }

    }

    /** This method will change machine label title to company name when radio button is pressed. */
    public void onOutSourced (ActionEvent actionEvent){
        if (outSourcedRadio.isSelected()) {
            this.label.setText("Company Name");
        }
    }

    /** This method sets each modified property in the selected part, called when the modifications are saved.
     *
     * An error I was initially getting was from using 'Integer.toString'. I was getting a NullPointerException thrown.
     * Initially I experimented with using a try-catch block and then realized that 'String.valueOf()'
     * did the same thing but without the tedious labor of checking for null.
     *
     * A future enhancement or feature that can further extend the functionality of this program would be to have a
     * database that automatically updates the product and part anytime there is a change in price, name, stock, e.t.c.
     * */
    public void sendPart(PartDTO part){
        id.setText(String.valueOf(part.getId()));
        name.setText(String.valueOf(part.getName()));
        stock.setText(String.valueOf(part.getStock()));
        priceCost.setText(String.valueOf(part.getPrice()));
        max.setText(String.valueOf(part.getMax()));
        min.setText(String.valueOf(part.getMin()));
        machineID.setText(String.valueOf(InHousePartDTO.getMachineID()));
    }

    /** This method checks validity of values in field, saves them if valid based on which radio button is pressed. */
    @FXML
    public void onSave(ActionEvent actionEvent) throws Exception {
        if(validate()) {
            try {
                if (inHouseRadio.isSelected()) {
                    PartDTO inHouse = new InHousePartDTO(Integer.parseInt(id.getText()),
                            name.getText(), Double.parseDouble(priceCost.getText()), Integer.parseInt(stock.getText()),
                            Integer.parseInt(min.getText()), Integer.parseInt(max.getText()), Integer.parseInt(machineID.getText()));

                    Inventory.updatePart(inHouse);
                    System.out.println("PartDTO was Modified!!");
                }
                if (outSourcedRadio.isSelected()) {
                    PartDTO outSourced = new OutSourcedPartDTO(Integer.parseInt(id.getText()),
                            name.getText(), Double.parseDouble(priceCost.getText()), Integer.parseInt(stock.getText()),
                            Integer.parseInt(min.getText()), Integer.parseInt(max.getText()), machineID.getText());

                    Inventory.updatePart(outSourced);
                    System.out.println("PartDTO was Modified!!");

                }
                Parent addParts = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
                Scene scene = new Scene(addParts);

                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning alert");

                alert.setHeaderText("INCORRECT DATA TYPE");
                alert.setContentText("All text fields must have the correct data type");

                alert.showAndWait();
            }
        }
    }

    /**  This method validates the field entries before save. */
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

    /** This method cancels any changes that are done in the Add Part Scene when the cancel button is pressed. */
    @FXML
    public void onCancel(ActionEvent actionEvent) throws Exception {
        Parent addParts = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Scene scene = new Scene(addParts);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }


}

