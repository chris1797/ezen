package com.ezen.demo.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.demo.mappers.MybatisBoardMapper;
import com.ezen.demo.model.Board;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Controller
@RequestMapping("/mybatis/board")
public class MybatisBoardController {
	
	@Autowired
	private MybatisBoardMapper dao;
	
	@GetMapping("/{i}")
	public String getBoardList(@PathVariable("i")int i, Model model) {
		PageHelper.startPage(i, dao.getListAll().size()/3);
		PageInfo<Board> pageInfo = new PageInfo<>(dao.getListAll());
		List<Board> list = pageInfo.getList();
		
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("list", dao.getListAll());
		
		return "mybatis_board/Myboard_list";
	}
	
	@GetMapping("/write_form")
	public String write_form(Model model) {
		Board board = new Board();
		model.addAttribute("board", board);
		return "mybatis_board/Myboard_inputform2";
	}
	
	@GetMapping("/save")
	@ResponseBody
	public Map<String,Object> write(Board board) {
		Map<String,Object> map = new HashMap<String, Object>();
		boolean saved = dao.write(board)>0;
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
		
		boolean updated = dao.update(board)>0;
		map.put("updated", updated);
		return map;
	}
	
	@GetMapping("/edit/{num}")
	public String edit(@PathVariable("num")int num, Model model) {
		model.addAttribute("board", dao.getBoardByNum(num));
		return "mybatis_board/Myboard_edit";
	}
	
	@PostMapping("/remove/{num}")
	@ResponseBody
	public Map<String, Object> remove(@PathVariable("num")int num) {
		Map<String,Object> map = new HashMap<String, Object>();
		
		boolean removed = dao.remove(num)>0;
		map.put("removed", removed);
		return map;
	}
	
	@GetMapping("/detail/{num}")
	public String getNum(@PathVariable("num")int num, Model model) {
		model.addAttribute("board", dao.getBoardByNum(num));
		return "mybatis_board/Myboard_detail";
	}
	
	@GetMapping("/search")
	public String searchlist(@RequestParam("category")String category, 
							@RequestParam("keyword")String keyword, 
							@RequestParam("page")int page, Model model) {
		System.out.println(page);
		System.out.println(category);
		System.out.println(keyword);
		
		Map<String, String> map = new HashMap<>();
		map.put("category", category);
		map.put("keyword", keyword);
		
		PageHelper.startPage(page, dao.searchlist(map).size()/3);
		
		PageInfo<Board> pageInfo = new PageInfo<>(dao.searchlist(map));
		
		model.addAttribute("category", category);
		model.addAttribute("keyword", keyword);
		model.addAttribute("pageInfo", pageInfo);
//		model.addAttribute("list", dao.searchlist(category, keyword));
		return "mybatis_board/Myboard_searchlist";
	}

}
