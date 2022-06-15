package com.ezen.board;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.*;

public class BoardDAO {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	private CallableStatement cstmt;
	
//===========================getConn================================//
	private Connection getConn() {
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver"); ojdbc8.jar
			Connection conn = DriverManager.getConnection(
	                   "jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//============================closeAll()===================================//
	private void closeAll() {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//=============================getList==================================//
	public List<BoardVO> getList() {
		conn = getConn();
		try {
			String sql = "SELECT * FROM BBS ORDER BY NUM DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<BoardVO> list = new ArrayList<>();
			
			while(rs.next()) {
				int num = rs.getInt("NUM");
				String title = rs.getString("TITLE");
				String contents = rs.getString("CONTENTS");
				String author = rs.getString("AUTHOR");
				java.sql.Date wdate = rs.getDate("WDATE");
				int pcode = rs.getInt("PCODE");
				
				BoardVO board = new BoardVO();
				board.setNum(num);
				board.setTitle(title);
				board.setContents(contents);
				board.setAuthor(author);
				board.setWdate(wdate);
				board.setPcode(pcode);
				
				list.add(board);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}
//=============================WRITE==================================//
	public boolean write(BoardVO board) {
		conn = getConn();
		String sql = "INSERT INTO BBS VALUES(BBS_NUM.NEXTVAL,?,?,?,SYSDATE,?)";
		try { // NUM, TITLE, CONTENTS, AUTHOR, WDATE, PCODE
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContents());
			pstmt.setString(3, board.getAuthor());
			pstmt.setInt(4, board.getPcode());
			return pstmt.executeUpdate() >0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return false;
	}
//=============================WRITE2==================================//
		public BoardVO write2(BoardVO board) {
			conn = getConn();
			String sql = "INSERT INTO BBS VALUES(BBS_NUM.NEXTVAL,?,?,?,SYSDATE,?)" +
						" RETURNING NUM INTO ?";
			try { // NUM, TITLE, CONTENTS, AUTHOR, WDATE, PCODE
				cstmt = conn.prepareCall("{call " + sql + "}");
				cstmt.setString(1, board.getTitle());
				cstmt.setString(2, board.getContents());
				cstmt.setString(3, board.getAuthor());
				cstmt.setInt(4, board.getPcode());
				
				cstmt.registerOutParameter(5, Types.INTEGER);
				
				cstmt.executeUpdate();
				
				board.setNum(cstmt.getInt(5));
				return board;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeAll();
			}
			return null;
		}
//=============================getnum==================================//
	public BoardVO getNum(int num) {
		conn = getConn();
		try {
			String sql = "SELECT * FROM BBS WHERE NUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			BoardVO board = new BoardVO();
			while(rs.next()) {
				board.setNum(rs.getInt("NUM"));
				board.setTitle(rs.getString("TITLE"));
				board.setContents(rs.getString("CONTENTS"));
				board.setAuthor(rs.getString("AUTHOR"));
				board.setWdate(rs.getDate("WDATE"));
				board.setPcode(rs.getInt("PCODE"));
			}
			System.out.println(board.getNum());
			return board;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}
//=============================getnum==================================//
	public BoardVO getLastNum(String name) {
		conn = getConn();
		try {
			String sql = "SELECT * FROM BBS WHERE AUTHOR=? "
					+ "AND NUM=(SELECT MAX(NUM) FROM BBS WHERE AUTHOR=?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				BoardVO board = new BoardVO();
				board.setNum(rs.getInt("NUM"));
				board.setTitle(rs.getString("TITLE"));
				board.setContents(rs.getString("CONTENTS"));
				board.setAuthor(rs.getString("AUTHOR"));
				board.setWdate(rs.getDate("WDATE"));
				board.setPcode(rs.getInt("PCODE"));
				return board;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}
//=============================getpage==================================//
	public List<Map<String, Object>> getPage(int pageNum) {
		conn = getConn();
		try {
			String sql = "SELECT * FROM bbs_page CROSS JOIN " +
				"(SELECT CEIL(COUNT(*)/5) AS ttlpages FROM bbs)" +
				"WHERE page=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pageNum);
			rs = pstmt.executeQuery();
			List<Map<String, Object>> list = new ArrayList<>();
			
			while(rs.next()) {
				Map map = new HashMap<String, Object>();
				map.put("num", rs.getInt("NUM"));
				map.put("title", rs.getString("TITLE"));
				map.put("author",rs.getString("AUTHOR"));
				map.put("wdate",rs.getDate("WDATE"));
				map.put("pcode", rs.getInt("PCODE"));
				map.put("ttlpages", rs.getInt("TTLPAGES"));
				
				list.add(map);
			}
			return list;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}
}

