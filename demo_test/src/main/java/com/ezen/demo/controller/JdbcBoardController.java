package com.ezen.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.demo.dao.JdbcBoardDao;
import com.ezen.demo.model.Board;

@Controller
@RequestMapping("/jdbc/board")
public class JdbcBoardController {
	
	@Autowired
	private JdbcBoardDao dao;
	
	@GetMapping("/list")
	public String getBoardList(Model model) {
		model.addAttribute("list", dao.getListAll());
		return "board_list";
	}
	
	@GetMapping("/detail/{num}")
	public String getNum(@PathVariable("num")int num, Model model) {
		model.addAttribute("board", dao.getBoardByNum(num));
		return "board_detail";
	}
	
	@GetMapping("/write_form")
	public String write_form(Model model) {
		Board board = new Board();
		model.addAttribute("board", board);
		return "board_inputform2";
	}
	
	@GetMapping("/save")
	@ResponseBody
	public Map<String,Object> write(Board board) {
		Map<String,Object> map = new HashMap<String, Object>();
//		Board newboard = new Board();
//		newboard.setTitle(board.getTitle());
//		newboard.setContents(board.getContents());
//		newboard.setAuthor(board.getAuthor());
//		newboard.setWdate(board.getWdate());
//		newboard.setPcode(board.getPcode());
		
		boolean saved = dao.write(board);
		map.put("saved", saved);
		return map;
	}
	
	@PostMapping("/update/{num}")
	@ResponseBody
	public Map<String, Object> update(@PathVariable("num")int num, Board newBoard){
		Map<String,Object> map = new HashMap<String, Object>();
		Board board = dao.getBoardByNum(num);
		board.setTitle(newBoard.getTitle());
		board.setContents(newBoard.getContents());
		
		boolean updated = dao.update(board);
		map.put("updated", updated);
		return map;
	}
	
	@GetMapping("/edit/{num}")
	public String edit(@PathVariable("num")int num, Model model) {
		model.addAttribute("board", dao.getBoardByNum(num));
		return "board_edit";
	}
	
	@PostMapping("/remove/{num}")
	@ResponseBody
	public Map<String, Object> remove(@PathVariable("num")int num) {
		Map<String,Object> map = new HashMap<String, Object>();
		
		boolean removed = dao.remove(num);
		map.put("removed", removed);
		return map;
	}
	
}
