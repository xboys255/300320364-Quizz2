package com.company;

public abstract class Employee {
    private String name;
    private double sale;
    protected double commision;
    protected String empType;

    public Employee (String name, double sale, String empType){
        this.name = name;
        this.empType = empType;

        this.sale =sale;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSale() {
        return String.format("%.2f",sale);
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public String getCommision() {
        return String.format("%.2f",commision);
    }

    public void setCommision(double commision) {
        this.commision = commision;
    }
}
