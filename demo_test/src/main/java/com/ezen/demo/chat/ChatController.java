package com.ezen.demo.chat;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ezen.demo.model.User;

@Controller
@RequestMapping("/ws")
@SessionAttributes("uid")
public class ChatController
{
   @GetMapping("")
   @ResponseBody
   public String index()
   {
      return "WebSocket Test";
   }
   
   
   //별다른 뜻은 없고 /chat으로 요청받으면 chat.jsp를 주겠다는 뜻
   @RequestMapping(value="/chat", method=RequestMethod.GET)
    public String chat(Locale locale, Model model) {
        return "chat/chat";
    }
   
   @GetMapping("/login")
   public String login() {
	   return "chat/login_form";
   }
   
	@PostMapping("/logincheck") //로그인 데이터를 받겠다.
	@ResponseBody
	public Map<String,Object> logincheck(User user, Model model) {
		boolean logincheck = user.getUid() != null ? true : false; 
		
		model.addAttribute("uid", user.getUid());
		String uid = (String) model.getAttribute("uid");
		System.out.println("Controller : " + uid);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("logincheck", logincheck);
		return map;
	}
}