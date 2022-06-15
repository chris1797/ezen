package com.ezen.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.ezen.vo.UserVO;

public class UserDAO 
{
	private static final String path="D:/test/member.txt";

	public boolean login(UserVO user) 
	{
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = null;
			while((line=br.readLine())!=null) 
			{
				if(user.equals(new UserVO(line.split(" ")))) 
				{
					br.close();
					return true;
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public boolean add(UserVO newUser) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(path, true));
			pw.println(newUser);
			pw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getPwd(String uid)
	{
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = null;
			while((line=br.readLine())!=null) 
			{
				String[] token = line.split(" ");
				if(token[0].equals(uid)) 
				{
					br.close();
					return token[1];
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public boolean update(UserVO user) 
	{
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = null;
			List<UserVO> list = new ArrayList<>();
			while((line=br.readLine())!=null) 
			{
				UserVO fUser = new UserVO(line.split(" "));
				if(fUser.getUid().equals(user.getUid())) {
					list.add(user);


				}
				list.add(fUser);
			}
			return overwrite(list);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	private boolean overwrite(List<UserVO> list) 
	{
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(path));
			for(int i=0;i<list.size();i++) {
				pw.println(list.get(i));
			}
			pw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
}
