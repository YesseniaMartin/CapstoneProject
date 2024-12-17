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

class CustomerTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	
	// entity under test
	private Customer customer;
	
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
		customer = em.find(Customer.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		customer = null;
	}

	@Test
	void test_Shoe_basic_mapping() {
		assertNotNull(customer);
		assertEquals("John", customer.getFirstName());
		assertEquals("Doe", customer.getLastName());
	}
	
	@Test
	void test_Customer_OneToOne_User() {
		assertNotNull(customer);
		assertEquals(1, customer.getId());
		
		assertEquals("username", customer.getUser().getUsername());
		assertEquals(1, customer.getUser().getId());
	}

}
