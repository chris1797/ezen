package com.ezen.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ezen.demo.model.Board;
import com.ezen.demo.model.Emp;

@Repository
public class JdbcBoardDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
//	private int num;
//	private String title;
//	private String contents;
//	private String author;
//	private java.sql.Date wdate;
//	private int pcode;
	
	
	public List<Board> getListAll() {
		String sql = "SELECT * FROM BOARD";
		List<Board> list = jdbcTemplate.query(sql, (rs,i)->{
			Board board = new Board();
			board.setNum(rs.getInt("NUM"));
			board.setTitle(rs.getString("TITLE"));
			board.setContents(rs.getString("CONTENTS"));
			board.setAuthor(rs.getString("AUTHOR"));
			board.setWdate(rs.getDate("WDATE"));
			board.setPcode(rs.getInt("PCODE"));
			return board;
		});
		return list;
	}


	public boolean write(Board board) {
		String sql = "INSERT INTO BOARD (NUM, TITLE, CONTENTS, AUTHOR, WDATE, PCODE) VALUES(BOARD_NUM.NEXTVAL,?,?,?,SYSDATE,?)";
		int result = jdbcTemplate.update(sql, board.getTitle(), board.getContents(),board.getAuthor(), board.getPcode());
		return result > 0;
	}


	public Board getBoardByNum(int num) {
		String sql = "SELECT * FROM board WHERE num=?";
		List<Board> list = jdbcTemplate.query(sql, (rs,i)->{
			Board board = new Board();
			board.setNum(rs.getInt("NUM"));
			board.setTitle(rs.getString("TITLE"));
			board.setContents(rs.getString("CONTENTS"));
			board.setAuthor(rs.getString("AUTHOR"));
			board.setWdate(rs.getDate("WDATE"));
			board.setPcode(rs.getInt("PCODE"));
			return board;
		}, num);
		return list.get(0);
	}


	public boolean update(Board board) {
		String sql = "UPDATE board SET title=?, contents=? WHERE num=?";
		int result = jdbcTemplate.update(sql, board.getTitle(), board.getContents(), board.getNum());
		return result > 0;
	}


	public boolean remove(int num) {
		String sql = "DELETE FROM board WHERE num=?";
		int result = jdbcTemplate.update(sql, num);
		return result > 0;
	}
	
	
}
