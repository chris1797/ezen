package com.ezen.util;

import java.util.*;

import com.ezen.vo.ITBook;

public class BookCart 
{
	private List<ITBook> list = new ArrayList<>();
	
	public boolean save(ITBook book)
	{
		int idx = list.indexOf(book);
		if(idx==-1) {
			list.add(book);
		}else {
			int qty1 = list.get(idx).getQty();
			list.get(idx).setQty( qty1 + book.getQty());
		}
		return true;
	}
	
	public List<ITBook> getCart()
	{
		return list;
	}

	public int getTotal() 
	{
		int total = 0;
		for(int i=0;i<list.size();i++)
		{
			ITBook book = list.get(i);
			total += book.getPrice() * book.getQty();
		}
		return total;
	}

	public boolean updateQty(int idx, int qty) 
	{
		list.get(idx).setQty(qty);
		return true;
	}

	public boolean remove(int idx) 
	{
		ITBook removedBook = list.remove(idx);
		return removedBook != null;
	}
}
