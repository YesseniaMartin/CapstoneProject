package com.skilldistillery.onlineshoestore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.jpaonlineshoestore.entities.*;
import com.skilldistillery.onlineshoestore.data.*;

@Controller
public class CustomerController {
		private final CustomerDAO dao;	
		Customer findCustomer = new Customer();
		Customer addCustomer = new Customer();
		Customer updateCustomer = new Customer();
		boolean deleteCustomer = false;
		public CustomerController(CustomerDAO dao) {
			this.dao = dao;
		}
		
	@RequestMapping("findCustomerById.do")
	public ModelAndView findCustomerById(@RequestParam("id") int id) {
		ModelAndView mv = new ModelAndView();
		try {
			findCustomer = dao.findCustomerById(id);
		} catch (Exception e) {

			e.printStackTrace();
		}
		mv.addObject("customer", findCustomer);
		mv.setViewName("Show");
		return mv;
	}
	
	@PostMapping(path ={ "deleteCustomer.do"})
	public ModelAndView deleteCustomer(@RequestParam("customer") Customer customer) {
		ModelAndView mv = new ModelAndView();
		deleteCustomer = true;
		mv.addObject("deleteCustomer", dao.findCustomerById(customer.getId()));
		mv.setViewName("WEB-INF/deletecustomer.jsp");
		
		return mv;
		
	}
	@PostMapping(path = { "addCustomer.do"})
	public ModelAndView addCustomer(@RequestParam("customer") Customer customer) {
		ModelAndView mv = new ModelAndView();
		try {
			addCustomer = dao.addCustomer(customer);
		}  catch (Exception e) {

			e.printStackTrace();
		}
		mv.addObject("addCustomer", dao.addCustomer(customer));
		mv.setViewName("WEB-INF/addcustomer.jsp");
		return mv;
		
	}
	@RequestMapping(path ={"updateCustomer.do"})
	public ModelAndView updateCustomer(@RequestParam("id") int id, @RequestParam("customer") Customer customer) {
		ModelAndView mv = new ModelAndView();
		try {
			updateCustomer = dao.updateCustomer(id, customer);
		} catch (Exception e) {

			e.printStackTrace();
		}
		mv.addObject("updatecustomer", dao.updateCustomer(id, customer));
		mv.setViewName("WEB-INF/updatecustomer.jsp");
		return mv;
		
	}
}
