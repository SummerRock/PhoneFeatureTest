package com.example.libjava;

//step #1 - Create a funnctional interface.

interface FuncInt<Ob, X, Y, Z> {
    //contains one and only abstract method
    Ob func(X make, Y model, Z year);
}

//step #2 - Create a Generic class providing a constructor compatible with FunInt.func()'s definition

class Automobile<X, Y, Z> {
    //Automobile Member Variables
    private X make;
    private Y model;
    private Z year;

    //Automobile Constructor
    public Automobile(X make, Y model, Z year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    protected void what() {
        System.out.println("This Automobile is a " + year + " " + make + " " + model + ".");
    }
}

//step #3 - Create a Non-Generic class providing a constructor compatible with FunInt.func()'s definition

class Plane {

    //Automobile Member Variables
    private String make;
    private String model;
    private int year;

    //Plane Constructor

    public Plane(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;//Automatic unboxing
    }

    protected void what() {
        System.out.println("This Plane is a " + year + " " + make + " " + model + ".");
    }
}

//Step #3 - Class making use of method reference with generics
//https://mp.weixin.qq.com/s/SBbPfSA6B2Fy7mIG3pWkcg
public class ConstrRefGen {
    //Here is where the magic happens

    static <Ob, X, Y, Z> Ob factory(FuncInt<Ob, X, Y, Z> obj, X p1, Y p2, Z p3) {
        return obj.func(p1, p2, p3);
    }

    public static void main(String[] args) {
        System.out.println();
        //Example #1

        FuncInt<Automobile<String, String, Integer>, String, String, Integer> auto_cons = Automobile<String, String, Integer>::new;
        Automobile<String, String, Integer> honda = factory(auto_cons, "Honda", "Accord", 2006);
        honda.what();

        //Example #2
        FuncInt<Plane, String, String, Integer> plane_cons = Plane::new;
        Plane cessna = factory(plane_cons, "Cessna", "Skyhawk", 172);
        cessna.what();
        System.out.println();
    }
}