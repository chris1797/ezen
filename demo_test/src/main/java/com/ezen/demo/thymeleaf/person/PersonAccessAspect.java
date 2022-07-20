package com.ezen.demo.thymeleaf.person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Configuration
public class PersonAccessAspect {
	
	@Before("execution(* com.ezen.demo.thymeleaf.person.PersonService.*(..))")
	public void before(JoinPoint joinPoint) throws Exception {
		HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
		Object obj = session.getAttribute("uid");
		if(obj==null) {
			log.error("로그인 필요");
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
		} else {
			log.info("{} 로그인 검사 통과", obj.toString());
		}
		
	}
}


