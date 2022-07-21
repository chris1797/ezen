package com.ezen.demo.thymeleaf.person;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.binding.MapperMethod.MethodSignature;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
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
@ServletComponentScan
public class PersonAccessAspect {
	@Autowired
	private PersonService svc;
	
	long miliseconds = System.currentTimeMillis();
    Date date = new Date(miliseconds);
    
    private List<User_Track> list = new ArrayList<>();
	
	
	@Before("execution(* com.ezen.demo.thymeleaf.person.PersonService.add())")
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
	
	@AfterReturning(value="execution(* com.ezen.demo.thymeleaf.person.PersonService.*(..))", returning="result" )
	public void logtrace(JoinPoint joinPoint, Object result) {
		if(result != null) {
			HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
			Object obj = session.getAttribute("uid");
			
			User_Track t = new User_Track();
			
			t.setUser_id(obj.toString());
			t.setWdate(date);
			t.setUserlog(joinPoint.toString());
			
			list.add(t);
			log.error("list 저장 완료");
		}
		
		log.info("{} returned with value {}", joinPoint, result);
	}
	
	@After("execution(* com.ezen.demo.thymeleaf.person.PersonController.logout*(..))")
	public void afterlogout(JoinPoint joinPoint) throws Exception{
		
		svc.logsave(list);
	}
}


