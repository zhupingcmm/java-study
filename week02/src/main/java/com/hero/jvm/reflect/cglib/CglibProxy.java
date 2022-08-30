package com.hero.jvm.reflect.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    private Class<?> clazz;

    public <T> T getProxy(Class<?> clazz){
        this.clazz = clazz;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("********执行方法之前***********");

        Object result = method.invoke(clazz.newInstance(), args);
        System.out.println("********* result " + result + " ***********");
        System.out.println("********执行方法之后***********");
        return result;
    }
}
