package com.ezen.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.demo.aop.message.Message;
import com.ezen.demo.model.Empmail;
import com.ezen.demo.model.Mail;
import com.ezen.demo.model.User;
import com.ezen.demo.service.MailReader;
import com.ezen.demo.service.MailService;

@Controller
@RequestMapping("/mail")
@ServletComponentScan
@SessionAttributes("uid")
public class MailTestController {

	@Autowired
	private MailService svc;
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private MailReader reader;
	
	HttpSession session;
	
	
//=======================================================================================
	
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
		model.addAttribute("Empmail", new Empmail());
		model.addAttribute("uid", (String)session.getAttribute("uid"));
		return "thymeleaf/mail/mailform2";
	}
	
	@PostMapping("/send")
	@ResponseBody
	public String send(@RequestParam("files") MultipartFile[] mfiles, 
						Mail mail,
						Empmail emp,
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
		boolean sended = svc.sendAttachMail2(mail, emp.getEmpno());
		return "{\"sended\":" + sended +"}";
	}
	
	@GetMapping("/read")
	@ResponseBody
	public void read(Model model) {
		reader.readEmail();
	}
	
	@GetMapping("/delete/{num}")
	public void delete(@PathVariable int num) {
		Message message = new Message();
		
	}
	
//================================login check=======================================
	@GetMapping("/login")
	public String login() {
		return "thymeleaf/mail/login";
	}
	
	@GetMapping("/logout")
	@ResponseBody
	public Map<String,Object> logout(SessionStatus status) {
		session.invalidate();
		status.setComplete();
		
		Map<String,Object> map = new HashMap<>();
		map.put("logout", true);
		return map;
	}
	
	
	@PostMapping("/logincheck") //로그인 데이터를 받겠다.
	@ResponseBody
	public Map<String,Object> logincheck(User user, Model model, HttpSession se) {
		this.session = se;
		model.addAttribute("uid", user.getUid());
		context.setAttribute("service", svc);
		context.setAttribute("uid", session.getAttribute("uid"));
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("logincheck", true);
		return map;
	}

}
