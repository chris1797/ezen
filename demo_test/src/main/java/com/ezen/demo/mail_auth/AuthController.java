package com.ezen.demo.mail_auth;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;

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

import com.ezen.demo.service.MailService;

@Controller
@RequestMapping("/auth")
@ServletComponentScan
public class AuthController {

	private final MailService svc;
	
	@Autowired
	ServletContext context;
	
	@Autowired
	public AuthController(MailService svc) {
		this.svc = svc;
	}
	
//---------------------------------------------------------------------------------------------------------//
	
	@GetMapping("/form")
	public String signupform(Model model) {
		String email = null;
		
		if((String)context.getAttribute("email") != null) {
			email = (String)context.getAttribute("email");
		}
		
		model.addAttribute("email", email);
		return "thymeleaf/authmail/signupform";
	}
	
	@PostMapping("/email")
	@ResponseBody
	public Map<String, Object> auth(@RequestParam String email) {
		
		Map<String, Object> map = new HashMap<>();
		String code = svc.getRandomText();
		context.setAttribute("code", code);
		boolean send = true;
		try {
			send = svc.sendMimeMessage(email, code);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		map.put("send", send);
		return map;
	}
	
	@PostMapping("/req")
	public String signupform2(Model model, 
								@RequestParam String email, 
								@RequestParam String code) {
		String msg = null;
		if(code.equals((String)context.getAttribute("code"))) {
			msg =  "이메일 인증 완료";
		}
		context.setAttribute("email", email);
		
		model.addAttribute("email", email);
		model.addAttribute("msg2", msg);
		return "thymeleaf/authmail/signupform";
	}
	
}
