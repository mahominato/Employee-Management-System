package com.example.employeemansys;

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
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        // Для salary используем метод calculateSalary()
        salaryColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().calculateSalary()).asObject());

        employeeTable.setItems(employeeList);

        typeComboBox.setItems(FXCollections.observableArrayList("Full-time", "Part-time", "Contractor"));
    }

    @FXML
    protected void addEmployee() {
        String name = nameField.getText();
        String type = typeComboBox.getValue();

        if (name.isEmpty() || type == null) {
            showAlert("Validation Error", "Name and Type are required!");
            return;
        }

        try {
            switch (type) {
                case "Full-time" -> {
                    double annualSalary = Double.parseDouble(rateField.getText());
                    employeeList.add(new FullTimeEmployee(name, annualSalary));
                }
                case "Part-time" -> {
                    double hoursWorked = Double.parseDouble(hoursField.getText());
                    double hourlyRate = Double.parseDouble(rateField.getText());
                    employeeList.add(new PartTimeEmployee(name, hoursWorked, hourlyRate));
                }
                case "Contractor" -> {
                    double maxHours = Double.parseDouble(hoursField.getText());
                    double contractorRate = Double.parseDouble(rateField.getText());
                    employeeList.add(new Contractor(name, maxHours, contractorRate));
                }
                default -> showAlert("Validation Error", "Invalid employee type selected!");
            }
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Validation Error", "Please enter valid numbers!");
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
