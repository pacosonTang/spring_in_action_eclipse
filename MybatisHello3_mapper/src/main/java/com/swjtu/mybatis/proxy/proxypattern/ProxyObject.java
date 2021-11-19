package com.swjtu.mybatis.proxy.proxypattern;

/**
 * 抽象对象角色
 */
abstract class AbstractObject {
    public abstract void operation();
}
/**
 * 目标对象
 */
class TargetObject extends AbstractObject {
    public void operation() {
        System.out.println("Do Something!");
    }
}
/**
 * 代理对象
 */
public class ProxyObject extends AbstractObject {
    TargetObject targetObject = new TargetObject();

    @Override
    public void operation() {
        System.out.println("do sth before");
        targetObject.operation();
        System.out.println("do sth after");
    }

    public static void main(String[] args) {
        new ProxyObject().operation();
    }
}
