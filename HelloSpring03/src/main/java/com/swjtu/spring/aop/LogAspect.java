package com.swjtu.spring.aop;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//使用 @Order(2) 设置切面的优先级来解决，优先级值越小，优先级越高。
@Order(2)
@Aspect
@Component
public class LogAspect {
	
	/**
	 * 定义一个方法， 用于声明切入点表达式。一般地，该方法中再不需要添加其他的代码。
	 * 使用 @Pointcut 来声明切入点表达式 。
	 * 后面的其他通知直接使用 方法名来引用当前的切入点表达式。
	 */
	@Pointcut("execution(public int com.swjtu.spring.aop.ArithmeticCalculator.*(..))")
	public void declareJoinPointExpression() {
	}
	
	/** 前置通知 
	 * 在 com.swjtu.spring.aop.ArithmeticCalculator 接口的每一个实现类
	 * 的每一个方法开始之前执行一段代码；
	 */
	@Before("declareJoinPointExpression()") // 引用切入点表达式
	// 想知道链接细节， 放置连接点 JoinPoint 
	public void beforeMethod(JoinPoint joinPoint) {
		System.out.println("\nthis is a method with @Before. ");
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("方法参数为 = " + args);
	}
	
	/** 后置通知
	 * 在 com.swjtu.spring.aop.ArithmeticCalculator 接口的每一个实现类
	 * 的每一个方法开始之后执行一段代码；无论该方法是否存在异常。
	 */
	@After("declareJoinPointExpression()")
	// 想知道链接细节， 放置连接点 JoinPoint 
	public void afterMethod(JoinPoint joinPoint) {
		System.out.println("\nthis is a method with @After. ");
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("方法参数为 = " + args);
	}
	
	/** 返回通知：在方法正常结束后执行的代码；也就是说，当抛出异常时， 该方法不会执行； 
	 *  返回通知是可以访问到方法返回值的；
	 *  返回通知是通过 反射来实现的；
	 */
	@AfterReturning(value="declareJoinPointExpression()", 
			        returning="result")
	public void afterReturn(JoinPoint joinPoint, Object result) {
		System.out.println("\nthis is a method with @AfterReturning. ");
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("方法参数为 = " + args + ", result = " + result);
	}
	
	/** 异常通知：  调用目标方法抛出异常时 调用的代码；
	 * 可以访问到异常对象，且可以指定在出现特定异常时再执行通知代码；
	 * 
	 */
	@AfterThrowing(value="declareJoinPointExpression()", 
	        throwing="ex")
	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
		System.out.println("\nthis is a method with @AfterThrowing. ");
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("方法参数为 = " + args + ", exception info = " + ex);
	}
	
	/** 环绕通知：  需要携带  ProceedingJoinPoint 类型的参数；
	 * 环绕通知类似于 动态代理里的 全过程；ProceedingJoinPoint 类型的参数可以决定是否执行目标方法；
	 * 且环绕通知必须有返回值；返回值即为 目标方法的返回值；
	 */
	// Around 环绕通知功能是最强大的，但并不表示它是最常用的， 仅了解。
	@Around(value="declareJoinPointExpression()")
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
