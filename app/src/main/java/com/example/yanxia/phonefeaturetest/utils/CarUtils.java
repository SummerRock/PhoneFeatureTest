package com.example.yanxia.phonefeaturetest.utils;

import com.example.yanxia.phonefeaturetest.dataModel.Car;

import java.util.ArrayList;
import java.util.List;

public class CarUtils {

    private static final List<Car> CAR_LIST = new ArrayList<>();

    static {
        CAR_LIST.add(new Car(321433, "GTR32", "群马A0000"));
        CAR_LIST.add(new Car(312444, "FC", "群马A1111"));
        CAR_LIST.add(new Car(134412, "FD", "群马A2222"));
        CAR_LIST.add(new Car(452435, "五菱宏光", "群马A3333"));
        CAR_LIST.add(new Car(457465, "AE86", "群马A4444"));
    }

    public static List<Car> getCarList() {
        return CAR_LIST;
    }
}
