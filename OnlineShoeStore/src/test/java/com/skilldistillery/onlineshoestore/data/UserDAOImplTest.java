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
public class UserDAOImplTest {
	
	@Autowired
	private  UserDAO dao;



	@Test
	void test_create_update_delete() {
		EntityManager em;
		User newUser = new User();
		newUser.setUsername("newUser");
		newUser.setPassword("newUser");
		User created = dao.addUser(newUser);

		newUser = dao.findUserById(5);
		boolean deleteNewUser = dao.deleteUser(newUser);
		assertTrue(deleteNewUser);
		
		
		
	}

}

