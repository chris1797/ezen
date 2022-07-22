package com.ezen.demo.thymeleaf.person;

import java.sql.Date;
import java.util.List;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class MySessionHandler implements HttpSessionListener{
	
	PersonService svc;
	
	long miliseconds = System.currentTimeMillis();
    Date date = new Date(miliseconds);

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) 
    {
        log.info("-------Session Created--------");
        java.sql.Timestamp createTime = new java.sql.Timestamp(new java.util.Date().getTime());
        sessionEvent.getSession().setAttribute("create", createTime);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) 
    {
    	java.sql.Timestamp destroyTime = new java.sql.Timestamp(new java.util.Date().getTime());
        sessionEvent.getSession().setAttribute("destroy", destroyTime);
        
        HttpSession session = sessionEvent.getSession();
        Object obj = session.getAttribute("histList");
        if(obj==null) return;
        
        List<User_Track> list = (List<User_Track>) obj;
        log.info("세션 종료, 이동내역={}", list);
        
        // 리스너에서는 DI 이 작동하지 않으므로 콘트롤러에서 서비스 객체를 세션에 저장했다가 아래처럼 사용하면 됨
        PersonService svc = (PersonService)session.getAttribute("service");
        svc.logsave(list);  // 다수개의 엔티티를 동시에 저장 가능
        
        log.info("테이블에 저장 성공");
        log.info("-------Session Destroyed--------");
    }
	
}
