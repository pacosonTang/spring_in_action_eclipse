package com.swjtu.mybatis.proxy.springaop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAopMain {
    public static void main(String[] args) {
        // 获取上下文环境
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Waiter waiter = (Waiter)context.getBean("waiter"); // 从上下文中获取bean
        // 调用服务员原有方法
        waiter.greetTo("zhangsan");
        waiter.serveTo("zhangsan");
        // 通过切面已经将 Waiter 实现了 Seller 接口，所以可以强制转换
        Seller seller = (Seller) waiter;
        seller.sell("apple", "zhangsan");
    }
}



