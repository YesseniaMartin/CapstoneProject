package com.skilldistillery.onlineshoestore.data;
import com.skilldistillery.jpaonlineshoestore.entities.*;

import jakarta.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class CustomerDAOImplTest {
	
	@Autowired
	private  CustomerDAO dao;
	@Autowired
	private  UserDAO daouser;


	@Test
	void test_create_update_delete() { //FIXME
		
		Customer newCustomer = dao.findCustomerById(2);
		User user = daouser.findUserById(1);
		newCustomer.setFirstName("Mary");
		newCustomer.setLastName("Jane");
		newCustomer.setEmail("email");
		newCustomer.setPhone("phone");
		newCustomer.setUser(user);
		Customer created = dao.updateCustomer(2, newCustomer);

		Customer deleteCustomer = dao.findCustomerById(3);
		boolean deleteNewCustomer = dao.deleteCustomer(deleteCustomer);
		assertTrue(deleteNewCustomer);
		
		
		
	}

}