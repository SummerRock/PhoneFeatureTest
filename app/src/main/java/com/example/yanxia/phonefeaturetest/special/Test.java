package com.example.yanxia.phonefeaturetest.special;


import com.google.gson.Gson;

public class Test {

    public static void main(String[] args) {
        String a = "{\n" +
                "\t\t\"age\": 110,\n" +
                "\t\t\"name\": \"Json字符串\"\n" +
                "\t}";

        NewItem item = new Gson().fromJson(a, NewItem.class);

        if (item.age == 666) {
            System.out.println("so good 111");
        } else {
            System.out.println("not bad 333");
        }
    }

}
