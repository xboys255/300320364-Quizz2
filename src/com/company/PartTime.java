package com.company;

public class PartTime extends Employee {


    public PartTime(String name, double sale, String empType) {

        super(name, sale, empType);
        setCommision(sale*0.1);
    }


}
