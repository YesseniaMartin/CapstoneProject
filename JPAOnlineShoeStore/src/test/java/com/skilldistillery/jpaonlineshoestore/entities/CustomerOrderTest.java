package com.skilldistillery.jpaonlineshoestore.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class CustomerOrderTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	
	// entity under test
	private CustomerOrder order;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("OnlineShoeStore");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		order = em.find(CustomerOrder.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		order = null;
	}

	@Test
	void test_Shoe_basic_mapping() {
		assertNotNull(order);
		assertEquals(1, order.getId());
		assertEquals(2024, order.getDate().getYear());
	}

}
