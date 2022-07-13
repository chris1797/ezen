package com.ezen.demo.jpa.board;

import java.sql.Date;
import java.util.*;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/jpaboard")
public class JpaBoardController {
	
	final Logger logger = LoggerFactory.getLogger(getClass());

	long miliseconds = System.currentTimeMillis();
    Date date = new Date(miliseconds);
	
	
	@Autowired
	private BoardRepository BoardRepository; //JPA가 구현해놓은 객체
	
	@GetMapping("")
	public String jpaTest() {
		return "JPA Test";
	}
	
	@GetMapping("/inputform")
	public String inputform(Model model) {
		return "/jpa_board/board_inputform";
	}
	
	@PostMapping("/save")
	@ResponseBody
	public ResponseEntity<Boolean> saveBoard(Board board) {
		Board _board = new Board();
		_board.setTitle(board.getTitle());
		_board.setContents(board.getContents());
		_board.setWdate(date);
		Board savedBoard = BoardRepository.save(board); // 레코드 추가 (한 행 추가)
		boolean saved = board.getNum() == savedBoard.getNum();
		System.out.println(saved);
		return new ResponseEntity<>(saved, HttpStatus.OK); 
	}
	/*
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Board>> findAllBoard() {
		
		List<Board> Boardlist = BoardRepository.findAll();
		
		return new ResponseEntity<>(Boardlist, HttpStatus.OK); 
	}
	
	@GetMapping("/findbyid/{num}")
	public ResponseEntity<Board> findbyid(@PathVariable("num") int num) {
		
		Optional<Board> op = BoardRepository.findById(num);
		Board Board = op.get();
		if(op.isEmpty()) {
			//검색 실패
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<>(Board, HttpStatus.OK); 
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Board>> findByUname(@PathVariable("name") String uname){
		List<Board> Boardlist = BoardRepository.findByUname(uname);
		
		return new ResponseEntity<List<Board>>(Boardlist, HttpStatus.OK);
	}
	
	@GetMapping("/name_email/{name}/{email}")
	public ResponseEntity<List<Board>> findByUnameAndEmail(@PathVariable("name") String uname, 
														@PathVariable("email") String email){
		List<Board> Boardlist = BoardRepository.findByUnameAndEmail(uname, email);
		
		return new ResponseEntity<List<Board>>(Boardlist, HttpStatus.OK);
	}
	
	@GetMapping("/update/{num}/{name}/{email}")
	public ResponseEntity<Board> update(@PathVariable("num") int num,
										@PathVariable("name") String uname,
										@PathVariable("email") String email) {
		Board Board = new Board();
		Board.setNum(num);
		Board.setUname(uname);
		Board.setEmail(email);
		
		Board savedBoard = BoardRepository.save(Board); // 레코드 추가 (한 행 추가)
		
		return new ResponseEntity<>(savedBoard, HttpStatus.OK); 
	}
	
	@GetMapping("/delete/{num}")
	public String delete(@PathVariable("num") int num) {
		
		Optional<Board> op = BoardRepository.findById(num);
		
		if(op.isPresent()) {
			BoardRepository.deleteById(num);
			return "삭제 성공";
		} else {
			return "삭제 실패";
		}
	}
	@GetMapping("/jpql/{start}/{end}")
	public ResponseEntity<List<Board>> getAllBet(@PathVariable("start") int start,
												@PathVariable("start") int end) {
		
		return new ResponseEntity<>(BoardRepository.findAllNumBet(start, end), HttpStatus.OK);
	}
	 */
	
}
