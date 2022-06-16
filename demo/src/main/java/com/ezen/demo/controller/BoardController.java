package com.ezen.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.demo.model.Board;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@GetMapping("") //아무 파라미터 없이 localhost면 board가 돌아가게 하겠다.
	public String board() {
		//여기서 선언하는 것이 view의 이름
		return "board_inputform"; //이쪽으로 forward가 되는 것
	}
	
	@GetMapping("/input")
	public String input(Model model) {
		
		return "board_inputform";
	}
	
	@PostMapping("/save")
	@ResponseBody
	public Map<String,Object> save(Board board) {
		System.out.println("Title : " + board.getTitle());
		System.out.println("Contents : " + board.getContents());
		
		Map<String, Object> map = new HashMap<>();
		map.put("title", board.getTitle());
		map.put("contents", board.getContents());
		
		return map;
	}
}
