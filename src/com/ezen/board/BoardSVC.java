package com.ezen.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.util.PageUtil.PageUtil;

public class BoardSVC {
	private HttpServletRequest request;
	private HttpServletResponse response;

	public BoardSVC(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public String process() {
		String cmd = request.getParameter("cmd");
		if(cmd==null || cmd.equals("list")) {
			if(request.getParameter("page") != null) {
				int pageNum = Integer.parseInt(request.getParameter("page"));
				
				BoardDAO dao = new BoardDAO();
				List<Map<String, Object>> list = dao.getPage(pageNum);
				
				int ttlpages = (int) list.get(0).get("ttlpages");
				PageUtil pageUtil = new PageUtil(pageNum, ttlpages);
				
				request.setAttribute("pageutil", pageUtil); //객체를 보낼수도 있다.
				request.setAttribute("list", list);
				return "/boardList_page.jsp";
			} else {
			BoardDAO dao = new BoardDAO();
			List<BoardVO> list = dao.getList();
			
			request.setAttribute("list", list);
			return "/boardList.jsp";
			}
		}
//=============================================================================//
		else if(cmd.contentEquals("inputform")) {
			return "/boardInputForm.jsp";
		}
//=============================================================================//
		else if(cmd.equals("write")) {
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
//			String author = request.getParameter("author");
//			java.sql.Date wdate = Date.valueOf(request.getParameter("wdate"));
//			String pcode = request.getParameter("pcode");
			
			BoardVO board = new BoardVO();
			board.setTitle(title);
			board.setContents(contents);
			board.setAuthor("이재훈");
//			board.setWdate(wdate);
//			board.setPcode(pcode);
			
			BoardDAO dao = new BoardDAO();
			BoardVO addedBoard = dao.write2(board);
			boolean added = addedBoard!=null;
			int num = addedBoard.getNum();
//			boolean added = dao.write(board);
			
			try {
				PrintWriter out = response.getWriter();
				out.printf("{\"added\":%b, \"num\":%d}", added, num);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return null;
		}
//=============================================================================//
		else if(cmd.equals("content")) {
			int num = Integer.parseInt(request.getParameter("num"));
			
			BoardDAO dao = new BoardDAO();
			BoardVO board = dao.getNum(num);
			request.setAttribute("board", board);
			
			return "/board_detail.jsp";
		}
//=============================================================================//
		else if(cmd.equals("detail")) {
			String name = "이재훈";
			BoardDAO dao = new BoardDAO();
			BoardVO board = dao.getLastNum(name);
			
			request.setAttribute("board", board);
			
			return "/board_detail.jsp"; 
		}
//=============================================================================//
		
		return null;
	}

}
