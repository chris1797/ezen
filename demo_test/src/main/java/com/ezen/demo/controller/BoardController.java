package com.ezen.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ezen.demo.model.Board;
import com.ezen.demo.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService svc;
	
	@GetMapping("") //아무 파라미터 없이 localhost면 board가 돌아가게 하겠다.
	public String board(Model model) {
		Board b = new Board();
		model.addAttribute("board", b);
		//여기서 선언하는 것이 view의 이름
		return "board_inputform"; //이쪽으로 forward가 되는 것
	}
	
	@GetMapping("/input")
	public String input(Model model) {
		Board b = new Board();
		model.addAttribute("board", b);
		
		// 이처럼 해야 inputform에서 pcode를 hidden으로 보내도 에러가 안남
		return "board_inputform";
	}
	
	@PostMapping("/save")
	@ResponseBody
	public Map<String,Object> save(Board board, @SessionAttribute(name="uid", required=false) String uid) {
//		Map<String, Object> map = new HashMap<>();
//		map.put("title", board.getTitle());
//		map.put("contents", board.getContents());
		Map<String,Object> map = new HashMap<String,Object>();
		if(uid == null) {
			map.put("saved", false);
			map.put("msg", "로그인 후에 사용할 수 있습니다.");
			return map;
		}
		System.out.println(uid);
		boolean saved = svc.save(board);
		
		map.put("saved", saved);
		return map;
	}
}
