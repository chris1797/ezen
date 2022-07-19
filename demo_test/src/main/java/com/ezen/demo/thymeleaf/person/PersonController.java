package com.ezen.demo.thymeleaf.person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.result.ResultSetOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.demo.jpa.board.Board;
import com.ezen.demo.jpa.board.JpaBoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/person")
@Controller
public class PersonController {
	
	@Autowired
	private PersonService svc;
	

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
	public String add(@Valid Person person, BindingResult result) {
		if(result.hasErrors()) {

			return "thymeleaf/Person/person_input";
		}
		svc.add(person);
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
	
}
