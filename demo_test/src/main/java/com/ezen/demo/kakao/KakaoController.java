package com.ezen.demo.kakao;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/kakao")
@Controller
@ServletComponentScan
public class KakaoController {
	
	@GetMapping("/form")
	public String kakaotalk_form(Model model) {
		String key = "";
		String script = "'Kakao.Auth.login({\r\n"
				+ "			success : function(response) {\r\n"
				+ "				console.log(response);\r\n"
				+ "				alert('login success');\r\n"
				+ "			},\r\n"
				+ "			fail : function(error) {\r\n"
				+ "				console.log(error);\r\n"
				+ "				alert('login fail');\r\n"
				+ "			},'";
		model.addAttribute("key", key);
		model.addAttribute("script", script);
		return "thymeleaf/kakao/kakao_share_api_test";
	}

}
