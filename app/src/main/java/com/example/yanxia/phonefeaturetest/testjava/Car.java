package com.example.yanxia.phonefeaturetest.testjava;

class Car {
    void run() {
        System.out.println("car is running!");
    }
}

class Audi extends Car {

    void run() {
        System.out.println("Audi is running safely with 100km!");
    }

    public static void main(String args[]) {
        Car b = new Audi();    //向上转型
        b.run();
    }
}