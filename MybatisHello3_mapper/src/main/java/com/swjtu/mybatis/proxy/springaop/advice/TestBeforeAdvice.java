package com.swjtu.mybatis.proxy.springaop.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;


public class TestBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("before invoke");
        System.out.println("do busi");
        System.out.println("after invoke");
    }
}
