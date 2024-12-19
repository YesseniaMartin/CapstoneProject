package com.skilldistillery.onlineshoestore.data;

import java.util.List;

import com.skilldistillery.jpaonlineshoestore.entities.Customer;

public interface CustomerDAO {
	Customer addCustomer(Customer customer);
	boolean deleteCustomer(Customer customer);
	Customer updateCustomer(int id, Customer customer);
	Customer findCustomerById(int id);
	

}
