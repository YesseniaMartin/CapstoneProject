package com.skilldistillery.onlineshoestore.controllers;

import org.springframework.stereotype.Controller;

import com.skilldistillery.onlineshoestore.data.CartDAO;

@Controller
public class CartController {
	
	private final CartDAO cartDAO;
	
	public CartController(CartDAO cartDAO) {
		this.cartDAO = null;
		
	}

}
