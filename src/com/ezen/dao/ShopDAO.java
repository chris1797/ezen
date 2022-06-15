package com.ezen.dao;

import java.io.*;
import java.util.List;

import com.ezen.vo.ITBook;

public class ShopDAO 
{
	private static final String fpath = "D:/test/orders.txt";

	public boolean save(ITBook book) 
	{
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(fpath, true));
			pw.println(book);
			pw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean saveOrder(List<ITBook> list) 
	{
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(fpath, true));
			for(int i=0;i<list.size();i++)
			{
				ITBook book = list.get(i);
				pw.println(book);
			}
			pw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
}
