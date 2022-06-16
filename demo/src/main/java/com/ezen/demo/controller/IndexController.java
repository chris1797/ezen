package com.ezen.demo.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.demo.service.IndexService;

@Controller
@RequestMapping("/index")
public class IndexController{
	@Autowired
	private IndexService svc; //객체를 생성하지 않고 선언만 하여 사용, Autowired로 필요할 때 자동으로 객체가 완성
	
	//URL을 연결해줘야함
	@GetMapping("") //아무 파라미터 없이 localhost면 index가 돌아가게 하겠다.
	public String index() {
		//여기서 선언하는 것이 view의 이름
		return "index"; //이쪽으로 forward가 되는 것
	}

//	@GetMapping("/gugu")
	@RequestMapping(value="/gugu", method=RequestMethod.GET) // 위 @GetMapping과 같은 의미
//	POST 방식으로 했을 땐 index빼고는 전부 error
//	get, post 구분하지 않겠다고 하면 method=를 안하면 됨
	public String gugu(@RequestParam(value="dan", required=false, defaultValue="2")int dan, 
			Model model) { //개발자가 선언만 해주고 호출은 하지 않는다, 호출은 스프링에서 해줌
		
//		IndexService svc = new IndexService(request);
		model.addAttribute("list", svc.getGugu(dan)); //model에 저장하는것 = request에 저장하는 것
		return "gugu";
	}
	
	@GetMapping("/gugu/{dan}") 
	public String gugu2(@PathVariable("dan")int dan, Model model) { 
		model.addAttribute("list", svc.getGugu(dan));
		return "gugu";
	}
	
	@PostMapping("/res/dan/{num}") // 3 -> @PathVariable로 처리
	@ResponseBody
	public Map<String,Object> res(@PathVariable("num")int num) {
		//return "Hello"; //Hello jsp를 굳이 만들지 않고 로직이 돌아가는지 확인만 하고 싶다..(이 자체를 출력해도)
						//얘는 응답의 본체다. 라는 선언 필요 @ResponseBody
						// jsp를 경유하지 않으므로 ajax 요청 확인해 적절
		Map<String,Object> map = new HashMap<>();
		map.put("num", num);
		return map;
	}
	
	@PostMapping("/res/fruit/{fruit}") // 3 -> @PathVariable로 처리
	@ResponseBody
	public Map<String,Object> res(@PathVariable("fruit")String fruit) {
		//return "Hello"; //Hello jsp를 굳이 만들지 않고 로직이 돌아가는지 확인만 하고 싶다..(이 자체를 출력해도)
						//얘는 응답의 본체다. 라는 선언 필요 @ResponseBody
						// jsp를 경유하지 않으므로 ajax 요청 확인해 적절
		Map<String,Object> map = new HashMap<>();
		map.put("fruit", fruit);
		return map;
	}
}
