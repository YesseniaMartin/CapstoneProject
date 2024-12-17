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

class BrandTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	
	// entity under test
	private Brand brand;
	
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
		brand = em.find(Brand.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		brand = null;
	}

	@Test
	void test_Brand_basic_mapping() {
		assertNotNull(brand);
		assertEquals(1, brand.getId());
		assertEquals("Nike", brand.getName());
	
	}

}
