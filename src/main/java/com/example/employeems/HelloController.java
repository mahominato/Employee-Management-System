package com.example.employeems;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {

    @FXML
    private TextField nameField, hoursField, rateField;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee, String> nameColumn, typeColumn;

    @FXML
    private TableColumn<Employee, Double> salaryColumn;

    private final ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Настройка таблицы
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        employeeTable.setItems(employeeList);

        // Настройка ComboBox
        typeComboBox.setItems(FXCollections.observableArrayList("Full-time", "Part-time", "Contractor"));
    }

    @FXML
    protected void addEmployee() {
        String name = nameField.getText();
        String type = typeComboBox.getValue();
        double salary = 0.0;

        if (name.isEmpty() || type == null) {
            showAlert("Validation Error", "Name and Type are required!");
            return;
        }

        try {
            switch (type) {
                case "Full-time":
                    if (rateField.getText().isEmpty()) {
                        showAlert("Validation Error", "Salary is required for Full-time employees!");
                        return;
                    }
                    salary = Double.parseDouble(rateField.getText());
                    employeeList.add(new FullTimeEmployee(name, salary));
                    break;

                case "Part-time":
                    if (hoursField.getText().isEmpty() || rateField.getText().isEmpty()) {
                        showAlert("Validation Error", "Hours and rate are required for Part-time employees!");
                        return;
                    }
                    double hoursWorked = Double.parseDouble(hoursField.getText());
                    double hourlyRate = Double.parseDouble(rateField.getText());
                    if (hoursWorked < 0 || hourlyRate <= 0) {
                        showAlert("Validation Error", "Hours and rate must be positive!");
                        return;
                    }
                    employeeList.add(new PartTimeEmployee(name, hoursWorked, (int) hourlyRate));
                    break;

                case "Contractor":
                    if (hoursField.getText().isEmpty() || rateField.getText().isEmpty()) {
                        showAlert("Validation Error", "Max hours and rate are required for Contractors!");
                        return;
                    }
                    double maxHours = Double.parseDouble(hoursField.getText());
                    double contractorRate = Double.parseDouble(rateField.getText());
                    if (maxHours <= 0 || contractorRate <= 0) {
                        showAlert("Validation Error", "Max hours and rate must be positive!");
                        return;
                    }
                    employeeList.add(new Contractor(name, maxHours, (int) contractorRate));
                    break;

                default:
                    showAlert("Validation Error", "Invalid employee type selected!");
            }

            // Очистка полей после добавления
            clearFields();

        } catch (NumberFormatException e) {
            showAlert("Validation Error", "Please enter valid numbers for salary, hours, or rate!");
        }
    }

    private void clearFields() {
        nameField.clear();
        hoursField.clear();
        rateField.clear();
        typeComboBox.getSelectionModel().clearSelection();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
