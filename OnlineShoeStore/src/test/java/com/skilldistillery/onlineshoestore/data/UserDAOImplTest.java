package com.skilldistillery.onlineshoestore.data;
import com.skilldistillery.jpaonlineshoestore.entities.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
public class UserDAOImplTest {
	private static UserDAO dao;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		dao = new UserDAOImpl();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		dao = null;
	}

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

