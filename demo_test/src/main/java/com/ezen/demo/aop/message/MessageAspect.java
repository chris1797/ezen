package com.ezen.demo.aop.message;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ezen.demo.thymeleaf.person.PersonAccessAspect;
import com.ezen.demo.thymeleaf.person.User_Track;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Configuration
@ServletComponentScan
public class MessageAspect {
	@Autowired
	ServletContext context;
	
	@After("execution(* com.ezen.demo.aop.message.MessageController.msgList(*))")
	public void deletemsg(JoinPoint joinPoint) {
			HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
			String uid = (String)session.getAttribute("uid");
			
			context.removeAttribute("msglist");
			log.error("메모리 메시지 삭제 완료");
		}
		
	}