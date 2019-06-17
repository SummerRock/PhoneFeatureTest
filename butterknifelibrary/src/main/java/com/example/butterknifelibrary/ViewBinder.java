package com.example.butterknifelibrary;

//新创建的Activity$ViewBinder
public interface ViewBinder<T> {

    void bind(T target);
}