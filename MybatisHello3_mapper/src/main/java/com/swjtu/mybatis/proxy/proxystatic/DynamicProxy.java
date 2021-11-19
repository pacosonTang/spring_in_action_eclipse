package com.swjtu.mybatis.proxy.proxystatic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 */
public class DynamicProxy implements InvocationHandler {
    private Object targetObject;
    public Object newProxyInstance(Object targetObject) {
        this.targetObject = targetObject;
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke");
        proxy = method.invoke(targetObject, args);
        System.out.println("before after");
        return proxy;
    }

    public static void main(String[] args) {
        DynamicProxy dProxy = new DynamicProxy();
        //  会飞的鸟
        Flyable bird = (Flyable)dProxy.newProxyInstance(new Bird());
        bird.fly(1000);
        // 风筝
        Flyable kite = (Flyable)dProxy.newProxyInstance(new Kite());
        kite.fly(1000);
    }
}
