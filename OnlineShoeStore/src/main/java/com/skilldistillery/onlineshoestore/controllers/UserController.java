package com.skilldistillery.onlineshoestore.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.jpaonlineshoestore.entities.Cart;
import com.skilldistillery.jpaonlineshoestore.entities.Customer;
import com.skilldistillery.jpaonlineshoestore.entities.Shoe;
import com.skilldistillery.jpaonlineshoestore.entities.User;
import com.skilldistillery.onlineshoestore.data.CartDAO;
import com.skilldistillery.onlineshoestore.data.CustomerDAO;
import com.skilldistillery.onlineshoestore.data.ShoeDAO;
import com.skilldistillery.onlineshoestore.data.UserDAO;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	private UserDAO userDAO;
	private ShoeDAO shoeDAO;
	private CartDAO cartDAO;
	private CustomerDAO customerDAO;

	public UserController(UserDAO userDAO, ShoeDAO shoeDAO, CartDAO cartDAO, CustomerDAO customerDAO) {
		this.userDAO = userDAO;
		this.shoeDAO = shoeDAO;
		this.cartDAO = cartDAO;
		this.customerDAO = customerDAO;
	}

	
	/**
	 * Utility method to add cart count by fetching the number of items in 
	 * the user's cart and adding it to the model.
	 * @param session
	 * @param model
	 */
	private void addCartCountToModel(HttpSession session, Model model) {
		User user = (User) session.getAttribute("loggedInUser");
		int cartCount = 0;
		if (user != null) {
			Customer customer = customerDAO.findCustomerByUserId(user.getId());
			if (customer != null) {
				Cart cart = cartDAO.findCartByCustomerId(customer.getId());
				if (cart != null && cart.getInventoryItems() != null) {
					cartCount = cart.getInventoryItems().size();
				}
			}
		}
		model.addAttribute("cartCount", cartCount);
	}

	@GetMapping(path = { "/" })
	public String login(Model model, HttpSession session) {
		addCartCountToModel(session, model);
		return "login";
	}

	@PostMapping(path = "login.do")
	public String gettingLogin(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model, HttpSession session) {
		User findUser = userDAO.findByUsernameAndPassword(username, password);
		if (findUser == null) {
			model.addAttribute("errorMessage", "Invalid credentials");
			addCartCountToModel(session, model);
			return "login";
		}
		session.setAttribute("loggedInUser", findUser);
		return "redirect:home.do";
	}

	@GetMapping("home.do")
	public String showHome(Model model, HttpSession session) {
		System.out.println("Redirected to home.do");
		List<Shoe> shoes = shoeDAO.findAll();
		model.addAttribute("shoes", shoes);
		addCartCountToModel(session, model);
		return "home";
	}

	@GetMapping("search.do")
	public String search(@RequestParam(name = "keyword", required = false) String keyword, Model model, HttpSession session) {
		List<Shoe> shoes;
		if (keyword == null || keyword.trim().isEmpty()) {
			shoes = shoeDAO.findAll();
		} else {
			shoes = shoeDAO.findShoeByKeyword(keyword);
		}
		model.addAttribute("shoes", shoes);
		addCartCountToModel(session, model);
		return "shoe";
	}

	@GetMapping("logout.do")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		addCartCountToModel(session, model);
		return "redirect:/";
	}
}
