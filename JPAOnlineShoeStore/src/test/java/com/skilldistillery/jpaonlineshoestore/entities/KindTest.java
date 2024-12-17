
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

	class KindTest {
		
		private static EntityManagerFactory emf;
		private EntityManager em;
		
		// entity under test
		private Kind kind;
		
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
			kind = em.find(Kind.class, 1);
		}

		@AfterEach
		void tearDown() throws Exception {
			em.close();
			kind = null;
		}

		@Test
		void test_Actor_basic_mapping() {
			assertNotNull(kind);
			assertEquals("Running", kind.getName());
			
			
		
		}

	}

