package com.ezen.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.demo.model.Mail;
import com.ezen.demo.service.MailService;

@Controller
@RequestMapping("/mail")
@ServletComponentScan
public class MailTestController {

	@Autowired
	private MailService svc;
	
	@GetMapping("/test")
	@ResponseBody
	public String test() {
		// 구글 > 네이버메일로 간단한 text 메일 전달
		return svc.sendSimpleText() ? "메일 전송 성공" : "메일 전송 실패";
	}
	
	@GetMapping("/mimetest")
	@ResponseBody
	public String mime_test() {
		String msg = null;
		try {
			msg = svc.sendMimeMessage() ? "MIME 전송 성공" : "MIME 전송 실패";
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	@GetMapping("/auth")
	@ResponseBody
	public String auth_test() {
		String msg = null;
		try {
			msg = svc.sendauthMessage() ? "인증메일 전송 성공" : "인증메일 전송 실패";
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	@GetMapping("/auth/{code}")
	@ResponseBody
	public String auth_test(@PathVariable String code) {
		
		return code;
	}
	
	@GetMapping("/mailform")
	public String mail_form(Model model) {
		model.addAttribute("Mail", new Mail());
		return "thymeleaf/mail/mailform";
	}
	
	@PostMapping("/send")
	@ResponseBody
	public String send(@RequestParam("files") MultipartFile[] mfiles, 
						Mail mail,
						HttpServletRequest request) throws IOException {
		
		ServletContext context = request.getServletContext();
		String Path = context.getRealPath("/WEB-INF/files");
		
		for(int i=0; i < mfiles.length; i++) {
       	 
			String fileName = mfiles[i].getOriginalFilename();
			try {
				mfiles[i].transferTo(new File(Path+"/"+fileName));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
        }
		
		mail.setFiles(mfiles);
		boolean sended = svc.sendAttachMail(mail);
		return "{\"sended\":" + sended +"}";
	}
	
}
