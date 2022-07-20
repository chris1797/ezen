package com.ezen.demo.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Configuration
public class UserAccessAspect {
	
	// 이거 전체가 Aspect
	// 1번째 * 는 return type, ex) public String
	// 2번째 * 는 모든 클래스,
	// 3번째 * 는 모든 메소드. 따라서 *가 아닌 클래스/메소드명을 넣어도 됨
	/*
	@Before("execution(* com.ezen.demo.aop.*.*(..))") //언제 돌아가게 할 것인지
	public void before(JoinPoint joinPoint) {
		
		//Advice
		log.info(" Check for user access");
		log.info(" Allowed execution for {}", joinPoint);
	}
	
	@After("execution(* com.ezen.demo.aop.*.*(..))") //언제 돌아가게 할 것인지
	public void after(JoinPoint joinPoint) {
		
		//Advice
		log.info(" 실행 완료");
		log.info(" Completed execution for {}", joinPoint);
	}
	
	@AfterReturning("execution(* com.ezen.demo.aop.*.*(..))")
	public void afterReturning(JoinPoint joinPoint, Object result) { // 메인로직의 return 값이 result로 들어감
		
	}
	
	@Around("execution(* com.ezen.demo.aop.*.*(..))")
	public void aroundAdvice(ProceedingJoinPoint jp) throws Throwable   
	{  
		log.info("The method aroundAdvice() before invokation of the method " + jp.getSignature().getName() + " method");  
		try   
		{  
			jp.proceed();   // 실제 호출된 Core Concern 이 실행됨
			HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();

		}   
		finally   
		{  
		  
		}  
		log.info("The method aroundAdvice() after invokation of the method " + jp.getSignature().getName() + " method");  
	}
	*/

}


