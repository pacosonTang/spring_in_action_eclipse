package com.swjtu.mybatis.proxy.springaop;

public class SpringAopDef {
}
interface Waiter { // 服务员接口
    void greetTo(String client);
    void serveTo(String client);
}
class NaiveWaiter implements Waiter { // 服务员实现类
    public void greetTo(String client) {
        System.out.println("NaiveWaiter greet to " + client);
    }
    public void serveTo(String client) {
        System.out.println("NaiveWaiter serve to " + client);
    }
}
interface Seller {// 售货员接口
    int sell(String goods, String client);
}
class SmartSeller implements Seller { // 售货员实现类
    public int sell(String goods, String client) {
        System.out.println("a smart seller sells " + goods + " to " + client);
        return 100;
    }
}

