package com.swjtu.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// 使用 @Order(1) 设置切面的优先级来解决，优先级值越小，优先级越高。
@Order(1)
@Aspect
@Component
public class ValidationAspect {
	
	// 前置通知： 验证参数
	@Before("execution(public int com.swjtu.spring.aop.ArithmeticCalculator.*(..))")
	public void validateArgs(JoinPoint joinPoint) {
		System.out.println("this method is a method named validateArgs with @Before.");
		System.out.println("validate: " + Arrays.asList(joinPoint.getArgs()));
	}
}
