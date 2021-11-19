package com.swjtu.mybatis.proxy.proxystatic;

/**
 * 会飞接口
 */
interface Flyable {
    void fly(long ms);
}
/**
 * 会飞的鸟
 */
class Bird implements Flyable {
    @Override
    public void fly(long ms) {
        System.out.println("bird is flying.");
        try {
            Thread.sleep(ms);
        } catch (Exception e ) {
            System.out.println("bird睡眠异常");
        }
    }
}
/**
 * 会飞的风筝
 */
class Kite implements Flyable {
    @Override
    public void fly(long ms) {
        System.out.println("kite is flying.");
        try {
            Thread.sleep(ms);
        } catch (Exception e ) {
            System.out.println("kite 睡眠异常");
        }
    }
}
/**
 * 静态代理
 */
public class StaticProxy implements Flyable {
    private Flyable flyable;
    public StaticProxy(Flyable flyable) {
        this.flyable = flyable;
    }
    @Override
    public void fly(long ms) {
        System.out.println("before fly");
        flyable.fly(ms);
        System.out.println("after fly");
    }

    public static void main(String[] args) {
        new StaticProxy(new Kite()).fly(1000);
        new StaticProxy(new Bird()).fly(1000);
    }
}
