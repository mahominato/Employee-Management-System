package com.example.employeems;

public class FullTimeEmployee extends Employee {
    private double annualSalary;

    public FullTimeEmployee(String name, double annualSalary) {
        super(name);
        this.annualSalary = annualSalary;
    }

    @Override
    public double calculateSalary() {
        return annualSalary;
    }

    @Override
    public String getType() {
        return "Full-time";
    }

    @Override
    public double getSalary() {
        return calculateSalary();
    }
}
