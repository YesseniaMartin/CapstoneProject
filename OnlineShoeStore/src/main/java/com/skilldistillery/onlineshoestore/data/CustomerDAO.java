package com.skilldistillery.onlineshoestore.data;

import com.skilldistillery.jpaonlineshoestore.entities.Customer;

public interface CustomerDAO {
	Customer addCustomer(Customer customer);
	Customer deleteCustomer(Customer customer);
	Customer updateCustomer(Customer customer);
	Customer findCustomerById(int id);

}
