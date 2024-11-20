package com.example.employeems;

public abstract class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String getType();

    public abstract double calculateSalary();

    public abstract double getSalary();
}
