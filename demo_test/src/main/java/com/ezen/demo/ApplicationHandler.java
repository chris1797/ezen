package com.ezen.demo;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.ezen.demo.aop.message.Message;
import com.ezen.demo.aop.message.MessageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApplicationHandler implements ApplicationListener
{
   @Override
   public void onApplicationEvent(ApplicationEvent event) 
   {
      //log.info("애플리케이션 이벤트 발생={}", event);
      if(event instanceof ContextClosedEvent) {
    	 ContextClosedEvent e = (ContextClosedEvent) event;
    	 
    	 WebApplicationContext ctx = (WebApplicationContext) e.getApplicationContext();
         ServletContext context = ctx.getServletContext();
         Object obj = context.getAttribute("msg");
         
         if(obj == null) return;
         Message msg = (Message) obj;
         
         MessageService svc = (MessageService)context.getAttribute("service");
         svc.save(msg);
         log.info("애플리케이션 종료 이벤트");
      }
      else if(event instanceof ContextRefreshedEvent) {
         log.info("애플리케이션 리프레시 이벤트");
         ContextRefreshedEvent e = (ContextRefreshedEvent) event;
         
          ApplicationContext appContext = e.getApplicationContext();
          if (!(appContext instanceof WebApplicationContext)) return;
          
          WebApplicationContext ctx = (WebApplicationContext) e.getApplicationContext();
          ServletContext context = ctx.getServletContext();
          
          
      }
   }
}