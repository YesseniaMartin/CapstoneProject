package com.skilldistillery.onlineshoestore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.jpaonlineshoestore.entities.*;
import com.skilldistillery.onlineshoestore.data.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {
	
	private final CustomerDAO custDAO;
	private final CartDAO cartDAO;
	Customer findCustomer = new Customer();
	Customer addCustomer = new Customer();
	Customer updateCustomer = new Customer();
	boolean deleteCustomer = false;

	public CustomerController(CustomerDAO custDAO, CartDAO cartDAO) {
		this.custDAO = custDAO;
		this.cartDAO = cartDAO;
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

	@RequestMapping("findCustomerById.do")
	public ModelAndView findCustomerById(@RequestParam("id") int id) {
		ModelAndView mv = new ModelAndView();
		try {
			findCustomer = custDAO.findCustomerById(id);
		} catch (Exception e) {

			e.printStackTrace();
		}
		mv.addObject("customer", findCustomer);
		mv.setViewName("WEB-INF/show.jsp");
		return mv;
	}

	@PostMapping(path = { "deleteCustomer.do" })
	public ModelAndView deleteCustomer(@RequestParam("customer") Customer customer) {
		ModelAndView mv = new ModelAndView();
		deleteCustomer = true;
		mv.addObject("deleteCustomer", custDAO.findCustomerById(customer.getId()));
		mv.setViewName("WEB-INF/deletecustomer.jsp");

		return mv;

	}

	@PostMapping(path = { "addCustomer.do" })
	public ModelAndView addCustomer(@RequestParam("customer") Customer customer) {
		ModelAndView mv = new ModelAndView();
		try {
			addCustomer = custDAO.addCustomer(customer);
		} catch (Exception e) {

			e.printStackTrace();
		}
		mv.addObject("addCustomer", custDAO.addCustomer(customer));
		mv.setViewName("WEB-INF/addcustomer.jsp");
		return mv;

	}

	@RequestMapping(path = { "updateCustomer.do" })
	public ModelAndView updateCustomer(@RequestParam("id") int id, @RequestParam("customer") Customer customer) {
		ModelAndView mv = new ModelAndView();
		try {
			updateCustomer = custDAO.updateCustomer(id, customer);
		} catch (Exception e) {

			e.printStackTrace();
		}
		mv.addObject("updatecustomer", custDAO.updateCustomer(id, customer));
		mv.setViewName("WEB-INF/updatecustomer.jsp");
		return mv;

	}
}
