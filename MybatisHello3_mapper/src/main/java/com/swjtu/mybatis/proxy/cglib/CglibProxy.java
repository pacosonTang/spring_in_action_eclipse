package com.swjtu.mybatis.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

class Plane {
    public void fly(long ms) {
        System.out.println("plane is flying");
        try {
            Thread.sleep(ms);
        } catch(Exception e) {
            System.out.println("plane睡眠异常");
        }
    }
}
public class CglibProxy implements MethodInterceptor {
    private Object target;
    public CglibProxy(Object target) {
        this.target = target;
    }
    public Object getProxyInstance() {
        Enhancer enhancer = new Enhancer(); // 1 实例化工具类
        enhancer.setSuperclass(this.target.getClass()); // 设置父类对象
        enhancer.setCallback(this); // 设置回调函数
        return enhancer.create(); // 创建子类，也就是代理对象
    }
    // 拦截器方法
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before invoke");
        Object returnValue = method.invoke(target, objects);
        System.out.println("after invoke");
        return returnValue;
    }
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy(new Plane());
        Plane plane = (Plane)cglibProxy.getProxyInstance();
        plane.fly(1000);
    }
}
