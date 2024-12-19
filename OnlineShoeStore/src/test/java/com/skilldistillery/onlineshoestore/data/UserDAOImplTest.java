package com.skilldistillery.onlineshoestore.data;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.jpaonlineshoestore.entities.User;

import jakarta.persistence.EntityManager;
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

