package com.org;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Okunta Braide
 */

/** This class sets up the main screen for the Inventory Management System. */

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MainForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }

    public static void initialData(){
        PartDTO p1 = new InHousePartDTO(1, "Brakes", 15.00, 10, 1, 25, 100);
        Inventory.addPart(p1);
        PartDTO p2 = new InHousePartDTO(2, "Wheel", 11.00, 16, 1, 25, 150);
        Inventory.addPart(p2);
        PartDTO p3 = new InHousePartDTO(3, "Seat", 15.00, 10, 1, 25, 110);
        Inventory.addPart(p3);
        ProductDT0 pr1 = new ProductDT0(1000, "Giant Bike", 299.99, 5, 1, 25);
        Inventory.addProduct(pr1);
        ProductDT0 pr2 = new ProductDT0(1001, "Tricycle", 99.99, 3, 1, 25);
        Inventory.addProduct(pr2);
    }

    public static void main(String[] args) {
        initialData();
        launch();
    }
}