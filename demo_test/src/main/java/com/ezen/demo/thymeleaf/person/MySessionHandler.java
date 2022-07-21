package com.ezen.demo.thymeleaf.person;

import java.sql.Date;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@WebListener
public class MySessionHandler implements HttpSessionListener{
	
	PersonService svc;
	
	long miliseconds = System.currentTimeMillis();
    Date date = new Date(miliseconds);

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSessionListener.super.sessionCreated(se);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSessionListener.super.sessionDestroyed(se);
	}
	
}
