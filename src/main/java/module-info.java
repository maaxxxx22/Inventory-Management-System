module com.org.first_javafx_project {
    requires javafx.controls;
    requires javafx.fxml;


    exports com.org;
    opens com.org to javafx.fxml;
}