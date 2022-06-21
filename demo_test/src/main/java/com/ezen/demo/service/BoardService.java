package com.ezen.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.demo.dao.BoardDAO;
import com.ezen.demo.model.Board;

@Service
public class BoardService {

	@Autowired
	private BoardDAO dao;
	
	public boolean save(Board board) {
		return dao.save(board);
	}
}
