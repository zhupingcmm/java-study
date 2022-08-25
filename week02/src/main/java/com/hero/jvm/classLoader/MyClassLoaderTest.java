package com.hero.jvm.classLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        MyClassLoader myClassLoader = new MyClassLoader("C:\\java\\java-study\\week02\\target\\classes");
        Class<?> clazz = myClassLoader.loadClass("com.hero.jvm.classLoader.Test");
        if (clazz != null) {
            Object obj = clazz.newInstance();
            Method method = clazz.getMethod("sayHi");
            method.invoke(obj);
            System.out.println(clazz.getClassLoader().toString());
        }
    }
}
