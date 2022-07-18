package com.ezen.demo.jpa.board;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JpaBoardService {
	
	long miliseconds = System.currentTimeMillis();
    Date date = new Date(miliseconds);
	
	@Autowired
	private BoardRepository boardRepository; //JPA가 구현해놓은 객체

	
	public Page<Board> getList(Pageable pageable) {
		//List<Board> list = boardRepository.findAll();
		Page<Board> pageInfo = boardRepository.findAll(pageable);
		
		return pageInfo;
	}
	
	public int[] getLinkRange(Page<Board> pageInfo) {
		int start = 0;
		int end = 0;

		if (pageInfo.getNumber() - 2 < 0) {
			start = 0;
		} else {
			start = pageInfo.getNumber() - 2;
		}

		if (pageInfo.getTotalPages() < (start + 4)) {
			end = pageInfo.getTotalPages();
			start = (end - 4) < 0 ? 0 : (end - 4);
		} else {
			end = start + 4;
		}
		return new int[] { start, end };
	}


	public boolean add(Board board) {
		board.setWdate(date);
		log.trace("input_board={}", board.toString());
		
		Board addedboard = boardRepository.save(board);
		
		return addedboard.equals(board);
	}

	public boolean findById(int num) {
		Optional<Board> op = boardRepository.findById(num);
		Board board = op.get();
		if(op.isEmpty()) {
			return false;
		}
		return true;
	}


	public Board findByNum(int num) {
		return boardRepository.findByNum(num);
	}


	public boolean remove(int num) {
		Optional<Board> op = boardRepository.findById(num);
		
		if(op.isPresent()) {
			boardRepository.deleteById(num);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean update(Board board) {
		return 0 < boardRepository.update(board.getNum(), board.getTitle(), board.getContents());
	}

}
