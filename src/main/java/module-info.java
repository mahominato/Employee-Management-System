module com.example.employeems {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.employeems to javafx.fxml;
    exports com.example.employeems;
}