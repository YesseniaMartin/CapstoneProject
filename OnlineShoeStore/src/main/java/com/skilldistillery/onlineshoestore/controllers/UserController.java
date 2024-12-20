package com.skilldistillery.onlineshoestore.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.jpaonlineshoestore.entities.Shoe;
import com.skilldistillery.jpaonlineshoestore.entities.User;
import com.skilldistillery.onlineshoestore.data.ShoeDAO;
import com.skilldistillery.onlineshoestore.data.UserDAO;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	private UserDAO userDAO;
	private ShoeDAO shoeDAO;

	public UserController(UserDAO userDAO , ShoeDAO shoeDAO) {
		this.userDAO = userDAO;
		this.shoeDAO = shoeDAO;
	}

	@GetMapping(path = { "/" })
	public String login(Model model) {
		return "login";
	}

	@PostMapping(path = "login.do")
	public String gettingLogin(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model,  HttpSession session) {
		User findUser = userDAO.findByUsernameAndPassword(username, password);
		if (findUser == null) {
			model.addAttribute("errorMessage", "Invalid credentials");
			return "login";
		}
		session.setAttribute("loggedInUser", findUser);
		return "redirect:home.do";
	}
	
	@GetMapping("home.do")
	public String showHome(Model model) {
		System.out.println("Redirected to home.do");
	    List<Shoe> shoes = shoeDAO.findAll();
	    model.addAttribute("shoes", shoes);
	    return "home"; 
	}

	@GetMapping("search.do")
	public String search(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
	    List<Shoe> shoes;
	    if (keyword == null || keyword.trim().isEmpty()) {
	        shoes = shoeDAO.findAll();
	    } else {
	        shoes = shoeDAO.findShoeByKeyword(keyword);
	    }
	    model.addAttribute("shoes", shoes);
	    return "shoe"; 
	}
	
	@GetMapping("logout.do")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/";
	}
}
