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

    /**
     * 泛型方法的基本介绍
     *
     * @param tClass 传入的泛型实参
     * @return T 返回值为T类型
     * 说明：
     * 1）public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
     * 2）只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
     * 3）<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
     * 4）与泛型类的定义一样，此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。
     */
    public <T> T genericMethod(Class<T> tClass) throws InstantiationException, IllegalAccessException {
        T instance = tClass.newInstance();
        return instance;
    }

    public void test7() {
        try {
            Object obj = genericMethod(Class.forName("java.util.ArrayList"));
            System.out.println("泛型测试 class name is " + obj.getClass().getSimpleName());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public <T> void printMsg(T... args) {
        for (T t : args) {
            System.out.println("泛型测试 t is " + t);
        }
    }

    public void test8() {
        printMsg("111", 222, "aaaa", "2323.4", 55.55);
    }

    public void showKeyValue1(Generic<? extends Number> obj) {
        System.out.println("泛型测试 key value is " + obj.getKey());
    }

    public void test9() {
        Generic<String> generic1 = new Generic<String>("11111");
        Generic<Integer> generic2 = new Generic<Integer>(2222);
        Generic<Float> generic3 = new Generic<Float>(2.4f);
        Generic<Double> generic4 = new Generic<Double>(2.56);

        //这一行代码编译器会提示错误，因为String类型并不是Number类型的子类
        //showKeyValue1(generic1);

        showKeyValue1(generic2);
        showKeyValue1(generic3);
        showKeyValue1(generic4);
    }

    public static void main(String args[]) {
        GenericsTest genericsTest = new GenericsTest();
        // genericsTest.showClassCastException();
        // genericsTest.test2();
        genericsTest.test8();
    }
}
