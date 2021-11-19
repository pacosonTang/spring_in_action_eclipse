package com.swjtu.mybatis.proxy.springaop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class EnableSellerAspect {
    @DeclareParents(value="com.swjtu.mybatis.proxy.springaop.NaiveWaiter" // 切点-目标类
                            , defaultImpl = SmartSeller.class)  // 增强类
    public Seller seller; // 增强类接口
}
