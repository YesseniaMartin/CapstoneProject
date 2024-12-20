package com.skilldistillery.onlineshoestore.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.jpaonlineshoestore.entities.*;
import com.skilldistillery.onlineshoestore.data.*;

public class ShoeController {
	private final ShoeDAO dao; 
	Shoe addShoe = new Shoe();
	boolean deleteShoe = false;
	Shoe updateShoe = new Shoe();
	List<Shoe> findShoeByKeyword = new ArrayList<>();
	public ShoeController(ShoeDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping("findShoeByKeyword.do")
	public ModelAndView findShoeByKeyword(@RequestParam("keyword") String keyword) {
		ModelAndView mv = new ModelAndView();
		try {
			findShoeByKeyword = dao.findShoeByKeyword(keyword);
	 }  catch (Exception e) {

			e.printStackTrace();
		}
		mv.addObject("shoe", findShoeByKeyword);
		mv.setViewName("WEB-INF/shoedetails.jsp");
		return mv;
	}
	@PostMapping("deleteShoe.do")
	public ModelAndView deleteShoe(@RequestParam("shoe") Shoe shoe) {
		ModelAndView mv = new ModelAndView();
		try {
			deleteShoe =  dao.deleteShoe(shoe);
		}
		  catch (Exception e) {

				e.printStackTrace();
			}
		mv.addObject("shoe", deleteShoe);
		mv.setViewName("WEB-INF/cart.jsp");
		return mv;
	}
	@PostMapping("addShoe.do")
	public ModelAndView addShoe(@RequestParam("shoe") Shoe shoe) {
		ModelAndView mv = new ModelAndView();
		try {
			addShoe = dao.addShoe(shoe);
		} catch (Exception e) {

			e.printStackTrace();
		}
		mv.addObject("shoe", addShoe);
		mv.setViewName("WEB-INF/cart.jsp");
		return mv;
	}
	@PostMapping("updateShoe.do")
	public ModelAndView updateShoe(@RequestParam("shoe") Shoe shoe) {
		ModelAndView mv = new ModelAndView();
		try {
			addShoe = dao.updateShoe(shoe);
		} catch (Exception e) {

			e.printStackTrace();
		}
		mv.addObject("shoe", updateShoe);
		mv.setViewName("WEB-INF/cart.jsp");
		return mv;
		}
}