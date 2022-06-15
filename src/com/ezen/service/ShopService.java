package com.ezen.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.dao.ShopDAO;
import com.ezen.util.BookCart;
import com.ezen.vo.ITBook;

public class ShopService 
{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private BookCart cart;
	
	public ShopService(HttpServletRequest request, HttpServletResponse response) 
	{
		this.request = request;
		this.response = response;
		Object obj = this.request.getSession().getAttribute("cart");
		if(obj==null) {
			this.request.getSession().setAttribute("cart", new BookCart());
		}
	}

	public String process() 
	{
		String cmd = request.getParameter("cmd");
		if(cmd==null || cmd.equals("index")) {
			return "/shop/index.jsp";
		}
		else if(cmd.equals("prog"))
		{
			int pg = Integer.parseInt(request.getParameter("page"));
			return "/shop/prog_"+pg+".jsp";
		}
		else if(cmd.equals("order"))
		{
			BookCart cart = (BookCart)request.getSession().getAttribute("cart");
			List<ITBook> list = cart.getCart();
			ShopDAO dao = new ShopDAO();
			boolean ordered = dao.saveOrder(list);
			if(ordered) {
				cart.getCart().clear();
			}
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("{\"ordered\":"+ordered+"}");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}  // end of order{}
		else if(cmd.equals("tocart"))
		{
			ITBook book = getBookParam();
			BookCart cart = (BookCart)request.getSession().getAttribute("cart");
			boolean saved = cart.save(book);
			
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("{\"saved\":"+saved+"}");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // end of tocart{}
		else if(cmd.equals("showcart"))
		{
			BookCart cart = (BookCart)request.getSession().getAttribute("cart");
			List<ITBook> list = cart.getCart();
			request.setAttribute("list", list);
			request.setAttribute("total", cart.getTotal());
			return "/shop/showCart.jsp";
		}
		else if(cmd.equals("update_qty"))
		{
			int idx = Integer.parseInt (request.getParameter("idx"));
			int qty = Integer.parseInt (request.getParameter("qty"));
			BookCart cart = (BookCart)request.getSession().getAttribute("cart");
			boolean updated = cart.updateQty(idx,qty);
			int total = cart.getTotal();
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("{\"updated\":"+updated+", \"total\":" + total + "}");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(cmd.equals("remove"))
		{
			int idx = Integer.parseInt (request.getParameter("idx"));
			BookCart cart = (BookCart)request.getSession().getAttribute("cart");
			boolean removed = cart.remove(idx);
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("{\"removed\":"+removed+"}");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(cmd.equals("empty"))
		{
			BookCart cart = (BookCart)request.getSession().getAttribute("cart");
			cart.getCart().clear();
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("{\"empty\":"+true+"}");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	} // end of process()

	private ITBook getBookParam()
	{
		String title = request.getParameter("title");
		String pub = request.getParameter("pub");
		String pubdate = request.getParameter("pubdate");
		String author = request.getParameter("author");
		String _price = request.getParameter("price");
		String _qty = request.getParameter("qty");
		int price = Integer.parseInt(_price);
		int qty = Integer.parseInt(_qty);
		
		ITBook book = new ITBook();
		book.setTitle(title);
		book.setPub(pub);
		book.setPubdate(Date.valueOf(pubdate));
		book.setAuthor(author);
		book.setPrice(price);
		book.setQty(qty);
		
		return book;
	}
}
