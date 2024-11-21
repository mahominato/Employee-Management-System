module org.example.employeemansys {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.employeemansys to javafx.fxml;
    exports org.example.employeemansys;
    exports com.example.employeemansys;
    opens com.example.employeemansys to javafx.fxml;
}