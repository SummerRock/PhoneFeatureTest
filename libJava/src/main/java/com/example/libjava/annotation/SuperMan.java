package com.example.libjava.annotation;

import java.lang.annotation.Repeatable;

@interface Persons {
    Person[] value();
}

@Repeatable(Persons.class)
@interface Person {
    String role() default "";
}

@Person(role = "artist")
@Person(role = "coder")
@Person(role = "PM")
public class SuperMan {

}