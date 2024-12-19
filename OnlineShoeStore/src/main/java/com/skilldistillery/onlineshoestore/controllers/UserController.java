package com.skilldistillery.onlineshoestore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.jpaonlineshoestore.entities.User;
import com.skilldistillery.onlineshoestore.data.UserDAO;

import ch.qos.logback.core.model.Model;

@Controller
public class UserController {
	
	private UserDAO userDAO;
	
	public UserController(UserDAO dao) {
		this.userDAO = dao;
	}
	
	@GetMapping(path = { "/"})
		public String login(Model model) {
		return "login";
	}
	
	@GetMapping(path =  "login.do")
	public ModelAndView login() {
		User addUser = userDAO.findUserById(0);
		System.out.println(addUser);
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", addUser);
		mv.setViewName("login");
		return mv;
	}
}
