package com.example.yanxia.phonefeaturetest.dataModel;

public class Car {
    private final long productDate;
    private final String brand;
    /**
     * 车牌号
     */
    private final String licenseNumber;

    public Car(long productDate, String brand, String licenseNumber) {
        this.productDate = productDate;
        this.brand = brand;
        this.licenseNumber = licenseNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public long getProductDate() {
        return productDate;
    }

    public void run() {
        System.out.println("car is running!");
    }
}