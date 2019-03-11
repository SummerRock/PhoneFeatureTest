package com.example.yanxia.phonefeaturetest.testjava.generics;

import java.util.ArrayList;
import java.util.List;

public class GenericsTest {
    public void showClassCastException() {
        List arrayList = new ArrayList();
        arrayList.add("aaaa");
        // arrayList.add(100);

        for (int i = 0; i < arrayList.size(); i++) {
            String item = (String) arrayList.get(i);
            System.out.println("泛型测试 item = " + item);
        }
    }

    public void test2() {
        List<String> stringArrayList = new ArrayList<>();
        List<Integer> integerArrayList = new ArrayList<>();

        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();

        if (classStringArrayList.equals(classIntegerArrayList)) {
            System.out.println("泛型测试:类型相同");
        } else {
            System.out.println("泛型测试:类型不同");
        }
    }

    public void test3() {
        List<String> arrayList = new ArrayList<>();
        //arrayList.add(100); //在编译阶段，编译器就会报错
    }

    public void test4() {
        //泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
        //传入的实参类型需与泛型的类型参数类型相同，即为Integer.
        Generic<Integer> genericInteger = new Generic<>(123456);

        //传入的实参类型需与泛型的类型参数类型相同，即为String.
        Generic<String> genericString = new Generic<>("key_vlaue");
        System.out.println("泛型测试 key is " + genericInteger.getKey());
        System.out.println("泛型测试 key is " + genericString.getKey());
    }

    public void test5() {
        Generic generic = new Generic("111111");
        Generic generic1 = new Generic(4444);
        Generic generic2 = new Generic(55.55);
        Generic generic3 = new Generic(false);

        System.out.println("泛型测试 key is " + generic.getKey());
        System.out.println("泛型测试 key is " + generic1.getKey());
        System.out.println("泛型测试 key is " + generic2.getKey());
        System.out.println("泛型测试 key is " + generic3.getKey());
    }

    public void test6(Generic<Number> obj) {
        System.out.println("泛型测试 key value is " + obj.getKey());
    }

    public static void main(String args[]) {
        GenericsTest genericsTest = new GenericsTest();
        // genericsTest.showClassCastException();
        // genericsTest.test2();
        genericsTest.test5();
    }
}
