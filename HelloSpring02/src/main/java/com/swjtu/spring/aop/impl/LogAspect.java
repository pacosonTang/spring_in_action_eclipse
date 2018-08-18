package com.swjtu.spring.aop.impl;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// 日志切面: 把这个类声明为一个切面， 需要把该类放入到ioc容器中
// , 再声明为一个切面
// 还得告诉这个切面，在哪些类的哪些方法执行之前执行
@Aspect
@Component
public class LogAspect {
	
	// 声明该方法是一个前置通知：在哪些类的哪些方法执行之前执行
	// 即在目标方法开始执行之前执行
	
	// 详细写法
//	@Before("execution(public int com.swjtu.spring.aop.impl.ArithmeticCalculator.*(int, int ))")
	// 省略写法: 省略了 访问修饰符+方法返回值+具体类名
	@Before("execution(* com.swjtu.spring.aop.impl.*.*(int, int ))") 
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName(); // 方法名
		List<Object> args = Arrays.asList(joinPoint.getArgs()); // 方法参数
		System.out.println("the methodName = " + methodName + ",  args = " + args);
	}
	
	// 后置通知：在目标方法执行后（无论是否发生异常），执行的通知；
	// 在后置通知中还不能访问目标方法的执行结果；
	@After("execution(* com.swjtu.spring.aop.impl.*.*(int, int ))")
	public void afterMethod(JoinPoint joinPoint) {
		System.out.println("this is a method annotated with @After");
		String methodName = joinPoint.getSignature().getName(); // 方法名
		List<Object> args = Arrays.asList(joinPoint.getArgs()); // 方法参数
		System.out.println("后置通知的方法名 =" + methodName + ", 参数为 = " + args);
	}
}
