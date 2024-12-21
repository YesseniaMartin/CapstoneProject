package com.skilldistillery.onlineshoestore.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.jpaonlineshoestore.entities.Cart;
import com.skilldistillery.jpaonlineshoestore.entities.Customer;
import com.skilldistillery.jpaonlineshoestore.entities.Shoe;
import com.skilldistillery.jpaonlineshoestore.entities.User;
import com.skilldistillery.onlineshoestore.data.CartDAO;
import com.skilldistillery.onlineshoestore.data.CustomerDAO;
import com.skilldistillery.onlineshoestore.data.ShoeDAO;

import jakarta.servlet.http.HttpSession;

@Controller
public class ShoeController {
	
	private final ShoeDAO shoeDAO;
	private final CartDAO cartDAO;
	private final CustomerDAO customerDAO;
	Shoe addShoe = new Shoe();
	boolean deleteShoe = false;
	Shoe updateShoe = new Shoe();
	List<Shoe> findShoeByKeyword = new ArrayList<>();

	public ShoeController(ShoeDAO shoeDAO, CartDAO cartDAO, CustomerDAO customerDAO) {
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

	@RequestMapping("findShoeByKeyword.do")
	public ModelAndView findShoeByKeyword(@RequestParam("keyword") String keyword, HttpSession session, Model model) {
		ModelAndView mv = new ModelAndView();
		try {
			findShoeByKeyword = shoeDAO.findShoeByKeyword(keyword);
		} catch (Exception e) {

			e.printStackTrace();
		}
		mv.addObject("shoes", findShoeByKeyword);
		mv.setViewName("shoedetails");
		
		addCartCountToModel(session, model);
		
		return mv;
	}

	@PostMapping("deleteShoe.do")
	public ModelAndView deleteShoe(@RequestParam("shoe") Shoe shoe, HttpSession session, Model model) {
		ModelAndView mv = new ModelAndView();
		try {
			deleteShoe = shoeDAO.deleteShoe(shoe);
		} catch (Exception e) {

			e.printStackTrace();
		}
		mv.addObject("shoe", deleteShoe);
		mv.setViewName("cart");
		
		addCartCountToModel(session, model);
		
		return mv;
	}

	@PostMapping("addShoe.do")
	public ModelAndView addShoe(@RequestParam("shoe") Shoe shoe, Model model, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		try {
			addShoe = shoeDAO.addShoe(shoe);
		} catch (Exception e) {

			e.printStackTrace();
		}
		mv.addObject("shoe", addShoe);
		mv.setViewName("cart");
		
		addCartCountToModel(session, model);
		
		return mv;
	}

	@PostMapping("updateShoe.do")
	public ModelAndView updateShoe(@RequestParam("shoe") Shoe shoe, Model model, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		try {
			addShoe = shoeDAO.updateShoe(shoe);
		} catch (Exception e) {

			e.printStackTrace();
		}
		mv.addObject("shoe", updateShoe);
		mv.setViewName("cart");
		
		addCartCountToModel(session, model);
		
		return mv;
	}

	@GetMapping("viewDetails.do")
	    public String showShoes(@RequestParam("shoeId") int id, Model model, HttpSession session) {
		 Shoe shoe = shoeDAO.findShoeById(id);
		 if (shoe == null) {
			 model.addAttribute("error", "Shoe not found");
		        return "error";
		 }
		 model.addAttribute("shoe",shoe);
		 
		 addCartCountToModel(session, model);
		 
		 return"viewDetails";
	}
}
