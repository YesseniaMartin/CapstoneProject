package com.skilldistillery.onlineshoestore.controllers;

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

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

	private final CartDAO cartDAO;
	private final CustomerDAO custDAO;
	private final ShoeDAO shoeDAO;

	public CartController(CartDAO cartDAO, CustomerDAO custDAO, ShoeDAO shoeDAO) {
		this.cartDAO = cartDAO;
		this.custDAO = custDAO;
		this.shoeDAO = shoeDAO;

	}

	@GetMapping("cart.do")
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
        
	    if (cart == null) {
	        // If no cart exists yet, create one
	        cart = new Cart();
	        cart.setCustomer(customer);
	        cart = cartDAO.createCart(cart);
	    }

        // Add the customer and cart to the model
        model.addAttribute("customer", customer);
        model.addAttribute("cart", cart);

        return "cart"; 
	}
	
	@PostMapping("addToCart.do")
	public String addToCart(@RequestParam("shoeId") int shoeId, HttpSession session) {
	    User loggedInUser = (User) session.getAttribute("loggedInUser");
	    if (loggedInUser == null) {
	        return "redirect:/";
	    }

	    Customer customer = custDAO.findCustomerByUserId(loggedInUser.getId());
	    if (customer == null) {
	        return "redirect:home.do";
	    }

	    Shoe shoe = shoeDAO.findShoeById(shoeId);
	    if (shoe == null) {
	        return "redirect:home.do";
	    }

	    Cart cart = cartDAO.findCartByCustomerId(customer.getId());
	    if (cart == null) {
	        // If no cart exists yet, create one
	        cart = new Cart();
	        cart.setCustomer(customer);
	        cart = cartDAO.createCart(cart);
	    }

	    // Add the shoe to the cart
	    cartDAO.addShoeToCart(cart.getId(), shoe);

	    return "redirect:cart.do";
	}

}
