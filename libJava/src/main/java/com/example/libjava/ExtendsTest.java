package com.example.libjava;

class A {
    public String show(D obj) {
        return ("A and D");
    }

    public String show(A obj) {
        return ("A and A");
    }
}

class B extends A {
    public String show(B obj) {
        return ("B and B");
    }

    public String show(A obj) {
        return ("B and A");
    }
}

class C extends B {

}

class D extends B {
}

class ExtendsTest {
    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new B();
        B b = new B();
        C c = new C();
        D d = new D();
        System.out.println(a1.show(b));   //a1指向的类时A
        System.out.println(a1.show(c));
        System.out.println(a1.show(d));
        System.out.println(a2.show(b));     //a2指向的类时B，B类直接超类是A而且重写了show（A OBJ）。所以当传入的参数是B对象的时候，调用B类从写的方法。
        System.out.println(a2.show(c));
        System.out.println(a2.show(d));
        System.out.println(b.show(b));
        System.out.println(b.show(c));     //b指向的类是B，而C,D类的直接超类是B所以调用的是show（B OBJ）。
        System.out.println(b.show(d));
    }
}

