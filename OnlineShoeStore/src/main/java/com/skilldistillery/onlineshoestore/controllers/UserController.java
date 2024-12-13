package com.skilldistillery.onlineshoestore.controllers;

import org.springframework.stereotype.Controller;

import com.skilldistillery.onlineshoestore.data.UserDAO;

@Controller
public class UserController {
	
	private UserDAO userDAO;
	
	public UserController(UserDAO dao) {
		this.userDAO = dao;
	}

}
