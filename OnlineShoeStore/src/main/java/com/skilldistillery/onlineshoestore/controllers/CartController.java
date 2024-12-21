package com.skilldistillery.onlineshoestore.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.jpaonlineshoestore.entities.Cart;
import com.skilldistillery.jpaonlineshoestore.entities.Customer;
import com.skilldistillery.jpaonlineshoestore.entities.CustomerOrder;
import com.skilldistillery.jpaonlineshoestore.entities.Shoe;
import com.skilldistillery.jpaonlineshoestore.entities.User;
import com.skilldistillery.onlineshoestore.data.CartDAO;
import com.skilldistillery.onlineshoestore.data.CustomerDAO;
import com.skilldistillery.onlineshoestore.data.OrderDAO;
import com.skilldistillery.onlineshoestore.data.ShoeDAO;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

	private final CartDAO cartDAO;
	private final CustomerDAO custDAO;
	private final ShoeDAO shoeDAO;
	private final OrderDAO orderDAO;

	public CartController(CartDAO cartDAO, CustomerDAO custDAO, ShoeDAO shoeDAO, OrderDAO orderDAO) {
		this.cartDAO = cartDAO;
		this.custDAO = custDAO;
		this.shoeDAO = shoeDAO;
		this.orderDAO = orderDAO;

	}

	/**
	 * Utility method to add cart count by fetching the number of items in the
	 * user's cart and adding it to the model.
	 * 
	 * @param session
	 * @param model
	 */
	private void addCartCountToModel(HttpSession session, Model model) {
		User user = (User) session.getAttribute("loggedInUser");
		int cartCount = 0;
		if (user != null) {
			Customer customer = custDAO.findCustomerByUserId(user.getId());
			if (customer != null) {
				Cart cart = cartDAO.findCartByCustomerId(customer.getId());
				if (cart != null && cart.getInventoryItems() != null) {
					cartCount = cart.getInventoryItems().size();
				}
			}
		}
		model.addAttribute("cartCount", cartCount);
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

		addCartCountToModel(session, model);

		return "cart";
	}

	@PostMapping("addToCart.do")
	public String addToCart(@RequestParam("shoeId") int shoeId, HttpSession session, Model model) {
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

		addCartCountToModel(session, model);

		return "redirect:home.do";

	}

	@PostMapping("checkout.do")
	public String checkout(HttpSession session, Model model) {
		try {
	        // Retrieve the logged-in user
	        User loggedInUser = (User) session.getAttribute("loggedInUser");
	        if (loggedInUser == null) {
	            return "redirect:/"; // Redirect to login if not authenticated
	        }

	        // Fetch the associated customer
	        Customer customer = custDAO.findCustomerByUserId(loggedInUser.getId());
	        if (customer == null) {
	            model.addAttribute("errorMessage", "Customer not found.");
	            return "cart";
	        }

	        // Retrieve the cart
	        Cart cart = cartDAO.findCartByCustomerId(customer.getId());
	        if (cart == null || cart.getInventoryItems().isEmpty()) {
	            model.addAttribute("errorMessage", "Your cart is empty!");
	            addCartCountToModel(session, model);
	            return "cart"; // Return to cart with error message
	        }

	        // Create a new CustomerOrder
	        CustomerOrder order = new CustomerOrder();
	        order.setDate(LocalDate.now());
	        order.setConfirmationNumber(generateConfirmationNumber());

	        // Associate the order with the cart
	        cart.setOrder(order); // Set the CustomerOrder in Cart

	        // Persist the CustomerOrder via OrderDAO
	        orderDAO.addOrder(order);

	        // Update the Cart to associate with the CustomerOrder
	        cartDAO.updateCart(cart); // Merge changes to the cart

	        // Clear the cart
	        cartDAO.clearCart(cart.getId());

	        // Add cart count (should be 0 after clearing)
	        addCartCountToModel(session, model);

	        // Redirect to confirmation page
	        return "redirect:confirmation.do";

	    } catch (Exception e) {
	        e.printStackTrace();
	        model.addAttribute("errorMessage", "Failed to process checkout.");
	        addCartCountToModel(session, model);
	        return "cart"; // Return to cart with error message
	    }
	}

	@PostMapping("deleteCart.do")
	public String deleteCart(HttpSession session, Model model) {

		User loggedInUser = (User) session.getAttribute("loggedInUser");
		if (loggedInUser == null) {
			return "redirect:/";
		}

		Customer customer = custDAO.findCustomerByUserId(loggedInUser.getId());
		if (customer == null) {
			return "redirect:home.do";
		}

		// Retrieve the cart
		Cart cart = cartDAO.findCartByCustomerId(customer.getId());
		if (cart == null) {
			model.addAttribute("errorMessage", "No cart found to delete.");
			addCartCountToModel(session, model);
			return "cart";
		}

		cartDAO.clearCart(cart.getId());

		addCartCountToModel(session, model);

		model.addAttribute("successMessage", "Your cart has been cleared successfully!");

		return "cart";
	}
	

	/**
	 * Utility method to generate a unique confirmation number.
	 * 
	 * @return A unique confirmation number string.
	 */
	private String generateConfirmationNumber() {
		return "CONF" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
	}

	@GetMapping("confirmation.do")
	public String showConfirmation(Model model, HttpSession session) {
		// Cart count is now 0 after checkout
		addCartCountToModel(session, model);

		return "confirmation";
	}

}
