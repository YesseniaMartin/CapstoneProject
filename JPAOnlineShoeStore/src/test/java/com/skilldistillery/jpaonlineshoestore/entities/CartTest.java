package com.skilldistillery.jpaonlineshoestore.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class CartTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	
	// entity under test
	private Cart cart;
	
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
		cart = em.find(Cart.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		cart = null;
	}

	@Test
	void test_Cart_basic_mapping() {
		assertNotNull(cart);
		assertEquals(1, cart.getId());
		
	}
	
	@Test
	void test_Cart_ManyToMany_InventoryItem() {
		assertNotNull(cart);
		assertEquals(1, cart.getId());
		
		assertTrue(cart.getInventoryItems().size() > 0);
	}
	
	@Test
	void test_Cart_OneToOne_Customer() {
		assertNotNull(cart);
		assertEquals(1, cart.getId());
		
		assertEquals("John", cart.getCustomer().getFirstName());
		assertEquals("Doe", cart.getCustomer().getLastName());
	}
	
	@Test
	void test_Cart_OneToOne_CustomerOrder() {
		assertNotNull(cart);
		assertEquals(1, cart.getId());
		
		assertEquals("CONF123456", cart.getOrder().getConfirmationNumber());
		assertEquals(2024, cart.getOrder().getDate().getYear());
	}

}
