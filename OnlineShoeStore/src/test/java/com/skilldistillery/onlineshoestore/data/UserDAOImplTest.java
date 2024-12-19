package com.skilldistillery.onlineshoestore.data;
import com.skilldistillery.jpaonlineshoestore.entities.*;
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
		User user1 = new User();
		user1.setUsername("user1");
		user1.setPassword("user1");
		User created = dao.addUser(user1);
		assertNotNull(created);
		assertTrue(created.getId() > 2);
		
	}

}

