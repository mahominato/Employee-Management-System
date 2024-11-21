package com.example.employeemansys;

public class Contractor extends Employee {
    private double maxHours;
    private double contractorRate;

    public Contractor(String name, double maxHours, double contractorRate) {
        super(name);
        this.maxHours = maxHours;
        this.contractorRate = contractorRate;
    }

    @Override
    public double calculateSalary() {
        return maxHours * contractorRate;
    }

    @Override
    public String getType() {
        return "Contractor";
    }
}
