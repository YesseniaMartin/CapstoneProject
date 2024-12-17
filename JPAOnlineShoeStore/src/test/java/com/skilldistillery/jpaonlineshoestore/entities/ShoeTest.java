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

class ShoeTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	
	// entity under test
	private Shoe shoe;
	
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
		shoe = em.find(Shoe.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		shoe = null;
	}

	@Test
	void test_Shoe_basic_mapping() {
		assertNotNull(shoe);
		assertEquals(1, shoe.getId());
		assertEquals(30.99, shoe.getPrice());
	
	}
	
	@Test
	void test_Shoe_ManyToOne_Kind() {
		assertNotNull(shoe);
		assertEquals(1, shoe.getId());
		assertEquals(30.99, shoe.getPrice());
		
		assertEquals("Running", shoe.getType().getName());
		
	}
	
	
	void test_Shoe_ManyToOne_Brand() {
		assertNotNull(shoe);
		assertEquals(1, shoe.getId());
		assertEquals(30.99, shoe.getPrice());
		
		assertEquals("Oxfords", shoe.getBrand().getName());
	}
	

}
