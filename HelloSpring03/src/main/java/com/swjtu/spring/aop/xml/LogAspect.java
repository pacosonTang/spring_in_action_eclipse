package com.swjtu.spring.aop.xml;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LogAspect {
	
	// 想知道链接细节， 放置连接点 JoinPoint 
	public void beforeMethod(JoinPoint joinPoint) {
		System.out.println("\nthis is a method with @Before. ");
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("方法参数为 = " + args);
	}
	
	// 想知道链接细节， 放置连接点 JoinPoint 
	public void afterMethod(JoinPoint joinPoint) {
		System.out.println("\nthis is a method with @After. ");
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("方法参数为 = " + args);
	}
	
	public void afterReturn(JoinPoint joinPoint, Object result) {
		System.out.println("\nthis is a method with @AfterReturning. ");
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("方法参数为 = " + args + ", result = " + result);
	}
	
	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
		System.out.println("\nthis is a method with @AfterThrowing. ");
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("方法参数为 = " + args + ", exception info = " + ex);
	}
	
	public Object around(ProceedingJoinPoint joinPoint) {
		
		System.out.println("\nthis is a method with @Around. ");
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("方法名 = " + methodName +", 方法参数为 = " + args);
		
		try {
			// 前置通知
			System.out.println("@Around 中的前置通知~~~");
			// 执行目标方法
			Object result = joinPoint.proceed();
			return result;
		} catch (Throwable e) {
			e.printStackTrace();
			// 异常通知 
			System.out.println("@Around 中的异常通知~~~");
		} finally {
			// 后置通知：无论被调用方法是否抛出异常 都要调用 后置通知 @After 
			System.out.println("@Around 中的后置通知~~~");
		}
		return null;
	} 
}
