package com.ezen.demo.aop.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ezen.demo.model.User;
import com.ezen.demo.thymeleaf.person.PersonController;
import com.ezen.demo.thymeleaf.person.PersonService;
import com.ezen.demo.thymeleaf.person.UserTrackRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/msg")
@Controller
@SessionAttributes("uid")
@ServletComponentScan
public class MessageController {
	
	@Autowired
	private MessageService svc;
	
	@Autowired
	private MessageRepository msgRepository;
	
	@Autowired
	ServletContext context;
	
	HttpSession session;
	
//=======================================================================================
	
	@GetMapping("/index")
	public String index(Model model) {
		int countmsg = 0;
		if(context.getAttribute("msglist") != null) {
			List<Message> msglist = (List<Message>) context.getAttribute("msglist");
			countmsg = msglist.size();
		}
		String msg = countmsg + "개의 읽지 않은 메세지가 있습니다.";
		model.addAttribute("countmsg", msg);
		return "thymeleaf/Message/index";
	}
	
	@GetMapping("/list")
	public String msgList(Model model) {
		List<Message> msglist = (List<Message>) context.getAttribute("msglist");
		model.addAttribute("msglist", msglist);
		return "thymeleaf/Message/msglist";
	}
	
	
	@GetMapping("/sendform")
	public String sendform(Model model) {
		java.sql.Date wdate = new java.sql.Date(new java.util.Date().getTime());
		
		Message msg = new Message();
		msg.setFromid((String)session.getAttribute("uid"));
		msg.setWdate(wdate);
		model.addAttribute("message", msg);
		return "thymeleaf/Message/sendform";
	}
	
	@PostMapping("/send")
	public String send(Message msg, Model model) {
		context.setAttribute("msg", msg);
		return "thymeleaf/Message/index";
	}
	
	
	//================================login check=======================================
		@GetMapping("/login")
		public String login() {
			context.setAttribute("service", svc);
			return "thymeleaf/Message/login";
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
			
			String uid = (String) session.getAttribute("uid");
	          
	        List<Message> msglist = svc.getListByTOID(uid);
	        context.setAttribute("msglist", msglist);
			
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("logincheck", true);
			return map;
		}
	

}
