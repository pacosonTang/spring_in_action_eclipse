package com.swjtu.spring.aop.helloworld;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// 基于动态代理实现的数学计算器
public class ArithmeticCalculatorLoggingProxy {
	// 日志对象
	private Log log = LogFactory.getLog(this.getClass());
	// 要代理的对象
	private ArithmeticCalculator target;

	public ArithmeticCalculatorLoggingProxy(ArithmeticCalculator target) {
		this.target = target;
	}
	// 返回计算器日志代理对象
	public ArithmeticCalculator getCalculatorLogProxy() {
		ArithmeticCalculator proxy = null;
		
		// 代理对象由哪一个类加载器加载
		ClassLoader loader = target.getClass().getClassLoader();
		// 代理对象的Class类型，即其中有哪些方法 
		Class<ArithmeticCalculator>[] interfaces = new Class[]{ArithmeticCalculator.class};
		// 匿名内部类 
		// 当调用代理对象其中的方法时，该执行的代码
		InvocationHandler handler = new InvocationHandler() {
			/**
			 * proxy 正在返回的那个代理对象，一般情况下，在invoke 方法中都不使用该对象。
			 * method 正在被调用的方法
			 * args 调用方法时传入的参数 
			 */
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				// 这里会是死循环， 调用 proxy.toString() 就会再次触发 invoke 方法
				// 最终造成内存溢出  
 				// System.out.println("proxy.toString = " + proxy.toString()); 
				String methodName = method.getName();
				log.info("logger ==> the method " + methodName); // 日志
				try {
					// 前置通知 @Before
					Object result = method.invoke(target, args); // 执行方法
					// 返回通知 @AfterReturning
					log.info("logger ==> the method " + methodName + ", result = " + result); // 日志 
					return result;
				} catch (Exception e) {
					e.printStackTrace();
					// 异常通知， 可以访问到方法抛出的异常 
				} finally {
					// 后置通知 @After：因为无论是否目标方法抛出异常与否，后置通知@After 都可以被调用 ，所以放在了 finally 里面
				}
				return null;
			}
		}; 
		proxy = (ArithmeticCalculator) Proxy.newProxyInstance(loader, interfaces, handler);
		return proxy;
	}
}
