package com.hero.jvm.reflect.cglib;

public class DemoCglib {
    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        School school = proxy.getProxy(School.class);
        school.sayName();
    }
}
