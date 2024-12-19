package com.skilldistillery.onlineshoestore.data;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.jpaonlineshoestore.entities.Brand;
import com.skilldistillery.jpaonlineshoestore.entities.Kind;
import com.skilldistillery.jpaonlineshoestore.entities.Shoe;
import com.skilldistillery.jpaonlineshoestore.entities.User;
@SpringBootTest
public class ShoeDAOImplTest {
	
	@Autowired
	private  ShoeDAO dao;



	@Test
	void test_shoe_impl() {
		Brand brand = new Brand();
		brand.setName("Nike");
		Kind kind = new Kind();
		kind.setName("Running");
		Shoe newShoe = new Shoe();
		newShoe.setBrand(brand);
		newShoe.setType(kind);
		newShoe.setPrice(12.00);
		newShoe = dao.addShoe(newShoe);
		
		boolean deleteShoe = dao.deleteShoe(newShoe);
		assertTrue(deleteShoe);
		
		
		
		
	}

}

