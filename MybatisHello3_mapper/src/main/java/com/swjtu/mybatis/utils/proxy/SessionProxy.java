package com.swjtu.mybatis.utils.proxy;

import com.swjtu.mybatis.exp.FrameException;
import com.swjtu.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSessionFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SessionProxy implements InvocationHandler {
    private Object targetObject;
    public Object newProxyInstance(Object targetObject) {
        this.targetObject = targetObject;
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SqlSessionFactory sqlSessionFactory = MyBatisUtils.getSqlSessionFactory(String.valueOf(args[2]));
        if (sqlSessionFactory == null) {
            throw new FrameException("获取sql连接失败");
        }
        proxy = method.invoke(targetObject, args);
        System.out.println("before after");
        return proxy;
    }
}
