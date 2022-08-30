package com.hero.jvm.reflect.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy {

    public Object createObject(Class<?> clazz) {
        // 返回代理对象
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new JdkInvocationHandler(clazz));
    }

    /**
     * 实现InvocationHandler接口
     * 调用invoke方法
     */
    public static class JdkInvocationHandler implements InvocationHandler{
        private Class<?> clazz;

        public JdkInvocationHandler(Class<?> clazz){
            //目标对象赋值
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
