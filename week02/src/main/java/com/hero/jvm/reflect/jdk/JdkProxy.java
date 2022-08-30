package com.hero.jvm.reflect.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy {

    public Object createObject(Class<?> clazz) {
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new JdkInvocationHandler(clazz));
    }

    public static class JdkInvocationHandler implements InvocationHandler{
        private Class<?> clazz;

        public JdkInvocationHandler(Class<?> clazz){
            this.clazz = clazz;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("********执行方法之前***********");

            Object result = method.invoke(clazz.newInstance(), args);
            System.out.println("********* result " + result.toString() + " ***********");
            System.out.println("********执行方法之后***********");
            return result;
        }
    }
}
