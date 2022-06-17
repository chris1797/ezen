package com.ezen.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.demo.dao.UserDAO;
import com.ezen.demo.model.User;

@Service
public class UserService {
	@Autowired
	private UserDAO dao;
	
	public boolean login(User user) {
		return dao.login(user);
	}
}
