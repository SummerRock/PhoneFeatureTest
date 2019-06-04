package com.example.yanxia.phonefeaturetest.utils;

import androidx.annotation.NonNull;

import com.example.yanxia.phonefeaturetest.dataModel.People;

import java.util.List;

public class PeopleDataManager {
    public interface OnPeopleDataLoadedInterface {
        void onPeopleDataLoadFinish(@NonNull List<People> peopleList);
    }

    private static final String TAG = PeopleDataManager.class.getSimpleName();
    private static volatile PeopleDataManager ourInstance;
    private List<OnPeopleDataLoadedInterface> dataLoadedInterfaceList;

    public static PeopleDataManager getInstance() {
        if (ourInstance == null) {
            synchronized (PeopleDataManager.class) {
                if (ourInstance == null) {
                    ourInstance = new PeopleDataManager();
                }
            }
        }
        return ourInstance;
    }

    private PeopleDataManager() {

    }

    public People getDefaultPeople() {
        return new People(7777, "乔峰", "契丹", "湖人", CarUtils.getCarList());
    }
}
