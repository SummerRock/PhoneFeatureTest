package com.example.yanxia.phonefeaturetest.testjava;

import java.io.Serializable;

class Car implements Serializable {
    private long productDate;
    private String name;
    private String licenseNumber;

    public Car(long productDate, String name, String licenseNumber) {
        this.productDate = productDate;
        this.name = name;
        this.licenseNumber = licenseNumber;
    }

    public long getProductDate() {
        return productDate;
    }

    public void setProductDate(long productDate) {
        this.productDate = productDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    void run() {
        System.out.println("car is running!");
    }
}

class Audi extends Car {

    Audi(long productDate, String name, String licenseNumber) {
        super(productDate, name, licenseNumber);
    }

    void run() {
        System.out.println("Audi is running safely with 100km!");
    }

    public static void main(String args[]) {
        Car b = new Audi(123, "A4", "A1234");    //向上转型
        b.run();
    }
}