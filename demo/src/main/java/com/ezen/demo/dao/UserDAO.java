package com.ezen.demo.dao;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.stereotype.Repository;

import com.ezen.demo.model.User;

@Repository
public class UserDAO {
	private static final String fpath = "C:/test/users.txt";
	
	public boolean login(User user) {
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(fpath));
			String line = null;
			while((line=br.readLine())!=null) 
			{
				if(user.equals(new User(line.split(" ")))) 
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
}
