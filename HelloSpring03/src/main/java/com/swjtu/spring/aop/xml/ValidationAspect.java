package com.swjtu.spring.aop.xml;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// 验证参数的切面
public class ValidationAspect {
	
	public void validateArgs(JoinPoint joinPoint) {
		System.out.println("this method is a method named validateArgs with @Before.");
		System.out.println("validate: " + Arrays.asList(joinPoint.getArgs()));
	}
}
