package com.skilldistillery.onlineshoestore.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.jpaonlineshoestore.entities.Cart;
import com.skilldistillery.jpaonlineshoestore.entities.Customer;
import com.skilldistillery.jpaonlineshoestore.entities.CustomerOrder;
import com.skilldistillery.jpaonlineshoestore.entities.Shoe;



@SpringBootTest
public class CartDAOImplTest {

	@Autowired
	private  CartDAO daoCart;
	
	@Autowired
	private  CustomerDAO daoCust;
	
	@Autowired
	private  OrderDAO daoOrder;
	
	@Autowired
	private  ShoeDAO daoShoe;


	@Test
	void test_create_update_delete() { //FIXME
		
		Customer customer = daoCust.findCustomerById(1);
		assertEquals("John", customer.getFirstName());
		
		// Test findOrderById
		CustomerOrder order = daoOrder.findOrderById(1);
		assertEquals(1, order.getId());
		
		// Test getCartById
		Cart cart = daoCart.getCartById(2);
		assertEquals(2, cart.getId());
		
		// Test addShoeToCart
		Shoe shoe = daoShoe.findShoeById(1);
		Cart addShoeToCart = daoCart.addShoeToCart(2, shoe);
		//assertTrue();
		
		// Test removeShoeFromCart
		
		// Test clearCart
		
		
				
	}
}
