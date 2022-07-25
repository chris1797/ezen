package com.ezen.demo.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.demo.model.Mail;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class MailService {
	
	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	ResourceLoader resourceLoader;

	public boolean sendSimpleText() {
		
	      List<String> receivers = new ArrayList<>();
	      receivers.add("jaehun6004@naver.com");

	      //보낼 이메일들 배열화
	      String[] arrReceiver = (String[])receivers.toArray(new String[receivers.size()]);
	      
	      //SimpleMailMessage 단순한 text메일 보내는 객체
	      SimpleMailMessage simpleMailMessage = new SimpleMailMessage(); 
	      
	      simpleMailMessage.setTo(arrReceiver);
	      simpleMailMessage.setSubject("Spring Boot Mail Test");
	      simpleMailMessage.setText("스프링에서 메일 보내기 테스트");
//	      simpleMailMessage.setText("<a href='/mail/auth/"+ createRandomStr()+"'>인증</a>");
	      
	      sender.send(simpleMailMessage);
	      return true;
	}
	
	public boolean sendMimeMessage() throws javax.mail.MessagingException
	   {
	      MimeMessage mimeMessage = sender.createMimeMessage();

	      try {
	         InternetAddress[] addressTo = new InternetAddress[1];
	         addressTo[0] = new InternetAddress("jaehun6004@naver.com");

	         mimeMessage.setRecipients(Message.RecipientType.TO, addressTo);

	         mimeMessage.setSubject("마임 메시지 테스트");
	         
	         mimeMessage.setContent("<a href='http://naver.com'>확인</a>", "text/html;charset=utf-8");
	         
	         sender.send(mimeMessage);
	         return true;
	      } catch (MessagingException e) {
	         log.error("에러={}", e);
	      }

	      return false;
	   }
	
	public boolean sendauthMessage() throws javax.mail.MessagingException
	   {
	      MimeMessage mimeMessage = sender.createMimeMessage();

	      try {
	         InternetAddress[] addressTo = new InternetAddress[1];
	         addressTo[0] = new InternetAddress("jaehun6004@naver.com");
	         
	         String link = String.format("<a href='http://localhost/mail/auth/%s'>확인</a>", getRandomText());

	         mimeMessage.setRecipients(Message.RecipientType.TO, addressTo);

	         mimeMessage.setSubject("인증 메시지 테스트");
	         
	         mimeMessage.setContent(link, "text/html;charset=utf-8");
	         
	         sender.send(mimeMessage);
	         return true;
	      } catch (MessagingException e) {
	         log.error("에러={}", e);
	      }

	      return false;
	   }
	
	
	public boolean sendAttachMail(Mail mail) {
	// mail의 속성 : sender, title, contents, file
	      MimeMessage mimeMessage = sender.createMimeMessage();
	      
	      Multipart multipart = new MimeMultipart();

	      try {
	         InternetAddress[] addressTo = new InternetAddress[1];
	         addressTo[0] = new InternetAddress("jaehun6004@naver.com");

	         mimeMessage.setRecipients(Message.RecipientType.TO, addressTo);
	         
	         // Title
	         mimeMessage.setSubject(mail.getTitle());
	         
	         // Fill the message
	         BodyPart messageBodyPart = new MimeBodyPart();
	         
	         // Contents
	         messageBodyPart.setContent(mail.getContents(), "text/html;charset=utf-8");
	         
	         // bodypart를 multipart에 추가
	         multipart.addBodyPart(messageBodyPart);
	          
	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         
	         MultipartFile[] mfiles = mail.getFiles();
	         for(int i=0; i < mfiles.length; i++) {
	        	 
	        	 File file = new File("C:/Users/302-18/git/ezen/demo_test/src/main/webapp/WEB-INF/files/"+mfiles[i].getOriginalFilename());
	        	 FileDataSource fds = new FileDataSource(file);
	        	 messageBodyPart.setDataHandler(new DataHandler(fds));
	        	 String fileName = fds.getName();
	        	 messageBodyPart.setFileName(fileName);
	         }
	         
	         
	         multipart.addBodyPart(messageBodyPart);
	          
	         // Put parts in message
	         mimeMessage.setContent(multipart);
	         
	         sender.send(mimeMessage);
	         
	         return true;
	      } catch(Exception ex) {
	         log.error("에러={}", ex);
	      }
	      return false;
	}
	
	//====================================================================================//
	private String getRandomText() {
		UUID randomUUID = UUID.randomUUID();
		return randomUUID.toString().replace("-", "");
	}
	//====================================================================================//
	
}
