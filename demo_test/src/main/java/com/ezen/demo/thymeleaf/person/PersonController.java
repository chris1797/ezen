package com.ezen.demo.thymeleaf.person;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionListener;
import javax.validation.Valid;

import org.hibernate.result.ResultSetOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.ezen.demo.jpa.board.Board;
import com.ezen.demo.jpa.board.JpaBoardService;
import com.ezen.demo.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/person")
@Controller
@SessionAttributes("uid")
@ServletComponentScan
public class PersonController {
	
	HttpSession session;
	
	@Autowired
	private PersonService svc;
	
	@Autowired
	private UserTrackRepository userTrackRepository;
	

	@GetMapping("/list")
	public String getList(Pageable pageable, Model model) {
		Page<Person> pageInfo = svc.getList(pageable);
		List<Person> list = pageInfo.getContent();
		
		int[] linkRange = svc.getLinkRange(pageInfo);
		
		model.addAttribute("item", "list");
		model.addAttribute("list", list);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("start", linkRange[0]);
		model.addAttribute("end", linkRange[1]);
		return "thymeleaf/Person/person_list";
	}
	
	@GetMapping("/input")
	public String input(Model model) {
		model.addAttribute("person", new Person()); // form-back bean (폼을 되돌려줄 때 필요한 bean)
		return "thymeleaf/Person/person_input";
	}
	
	@PostMapping("/save")
	public String add(@Valid Person person, BindingResult result, Model model) {
		if(result.hasErrors()) {
			
			// 1
//			FieldError ageErr = result.getFieldError("age");
//			String errMsg = ageErr.getDefaultMessage();
//			log.error("age error={}", errMsg);
			
			// 2
			List<FieldError> ferrList = result.getFieldErrors();
			for(int i=0; i<ferrList.size(); i++) {
				FieldError fe = ferrList.get(i);
				String fname = fe.getField();
				String errMsg2 = fe.getDefaultMessage();
				log.error("{}.{}", fname, errMsg2);
			}
			
			// 3
//			List<ObjectError> list = result.getAllErrors();
//			for(int i=0; i<list.size(); i++) {
//				ObjectError oe = list.get(i);
//				String errMsg2 = oe.getDefaultMessage();
//				log.error("{}.{}", i+1, errMsg2);
//			}
			
			return "thymeleaf/Person/person_input";
		}
		try {
			svc.add(person);
		} catch(HttpClientErrorException e) {
			model.addAttribute("msg", "로그인 후에 사용할 수 있습니다.");
			return "thymeleaf/Person/person_login";
		}
		return "redirect:/person/list";
	}
	
	@GetMapping("/detail/{num}")
	public String detail(@PathVariable("num") int num, Model model) {
		Person person = svc.findByNum(num);
		model.addAttribute("person", person);
		return "thymeleaf/Person/person_detail";
	}
	
	@PostMapping("/update")
	public String update(Person person) {
		svc.update(person);
		return "redirect:/person/list";
	}
	
	@GetMapping("/delete/{num}")
	public String delete(@PathVariable("num") int num) {
		svc.delete(num);
		return "redirect:/person/list";
	}
	
//================================login check=======================================
	@GetMapping("/login")
	public String login() {
		return "thymeleaf/Person/person_login";
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
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("logincheck", true);
		return map;
	}
	
}
