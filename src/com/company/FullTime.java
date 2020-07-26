package com.company;

public class FullTime extends Employee {


    public FullTime(String name, double sale, String emType) {

        super(name, sale,emType);
        setCommision(sale * 0.15);
    }


}
