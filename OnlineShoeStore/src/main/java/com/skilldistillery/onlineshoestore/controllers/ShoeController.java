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

import com.skilldistillery.jpaonlineshoestore.entities.Shoe;
import com.skilldistillery.onlineshoestore.data.ShoeDAO;

@Controller
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
		} catch (Exception e) {

			e.printStackTrace();
		}
		mv.addObject("shoes", findShoeByKeyword);
		mv.setViewName("shoedetails");
		return mv;
	}

	@PostMapping("deleteShoe.do")
	public ModelAndView deleteShoe(@RequestParam("shoe") Shoe shoe) {
		ModelAndView mv = new ModelAndView();
		try {
			deleteShoe = dao.deleteShoe(shoe);
		} catch (Exception e) {

			e.printStackTrace();
		}
		mv.addObject("shoe", deleteShoe);
		mv.setViewName("cart");
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
		mv.setViewName("cart");
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
		mv.setViewName("cart");
		return mv;
	}

	@GetMapping("viewDetails.do")
	    public String showShoes(@RequestParam("shoeId") int id, Model model) {
		 Shoe shoe = dao.findShoeById(id);
		 if (shoe == null) {
			 model.addAttribute("error", "Shoe not found");
		        return "error";
		 }
		 model.addAttribute("shoe",shoe);
		 return"viewDetails";
	}
}
