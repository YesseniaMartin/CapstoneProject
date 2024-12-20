package com.skilldistillery.onlineshoestore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.skilldistillery.jpaonlineshoestore.entities.Cart;
import com.skilldistillery.jpaonlineshoestore.entities.Customer;
import com.skilldistillery.jpaonlineshoestore.entities.User;
import com.skilldistillery.onlineshoestore.data.CartDAO;
import com.skilldistillery.onlineshoestore.data.CustomerDAO;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

	private final CartDAO cartDAO;
	private final CustomerDAO custDAO;

	public CartController(CartDAO cartDAO, CustomerDAO custDAO) {
		this.cartDAO = cartDAO;
		this.custDAO = custDAO;

	}

	@GetMapping("/cart")
	public String showCart(HttpSession session, Model model) {

		// Get the logged-in user from the session
		User loggedInUser = (User) session.getAttribute("loggedInUser");

		if (loggedInUser == null) {
			// No user is logged in, redirect to login
			return "redirect:/";
		}

		// Get the customer for this user
		Customer customer = custDAO.findCustomerByUserId(loggedInUser.getId());
		if (customer == null) {
			// If no customer is found,redirect to home page
			return "redirect:home.do";
		}

		// Find the cart by customer ID
        Cart cart = cartDAO.findCartByCustomerId(customer.getId());

        // Add the customer and cart to the model
        model.addAttribute("customer", customer);
        model.addAttribute("cart", cart);

        return "cart"; 
	}

}
