package com.skilldistillery.onlineshoestore.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	private CartDAO daoCart;

	@Autowired
	private CustomerDAO daoCust;

	@Autowired
	private OrderDAO daoOrder;

	@Autowired
	private ShoeDAO daoShoe;

	@Test
	void test_create_update_delete() { // FIXME

		Customer customer = daoCust.findCustomerById(1);
		assertNotNull(customer);
		assertEquals("John", customer.getFirstName());

		// Test findOrderById
		CustomerOrder order = daoOrder.findOrderById(1);
		assertNotNull(order);
		assertEquals(1, order.getId());

		// Test getCartById
		Cart cart = daoCart.getCartById(2);
		assertNotNull(cart);
		assertEquals(2, cart.getId());

		int initialSize = cart.getInventoryItems().size();

		// Test addShoeToCart
		Shoe shoe = daoShoe.findShoeById(2);
		assertNotNull(shoe);
		Cart addShoeToCart = daoCart.addShoeToCart(cart.getId(), shoe);
		assertNotNull(addShoeToCart);
		// The size should now be initialSize + 1
		assertEquals(initialSize + 1, addShoeToCart.getInventoryItems().size());
		// Verify that the shoe we added is actually in the cart
		boolean shoeFound = addShoeToCart.getInventoryItems().stream()
				.anyMatch(item -> item.getShoeId().getId() == shoe.getId());
		assertTrue(shoeFound);

		// Test removeShoeFromCart
		boolean removed = daoCart.removeShoeFromCart(cart.getId(), shoe);
		assertTrue(removed);
		// Re-check the cart
		Cart updatedCartAfterRemoval = daoCart.getCartById(cart.getId());
		assertNotNull(updatedCartAfterRemoval);
		boolean shoeStillInCart = updatedCartAfterRemoval.getInventoryItems().stream()
				.anyMatch(item -> item.getShoeId().getId() == shoe.getId());
		assertFalse(shoeStillInCart); // Shoe should no longer be in the cart after removal
		// The size should now be back to initialSize
		assertEquals(initialSize, updatedCartAfterRemoval.getInventoryItems().size());

		// Test clearCart
		// First, let's add a shoe or two to ensure the cart is not empty
		daoCart.addShoeToCart(cart.getId(), shoe);
		daoCart.addShoeToCart(cart.getId(), shoe); // add the same shoe multiple times if allowed
		Cart cartBeforeClear = daoCart.getCartById(cart.getId());
		assertTrue(cartBeforeClear.getInventoryItems().size() > 0);
		daoCart.clearCart(cart.getId());

		Cart clearedCart = daoCart.getCartById(cart.getId());
		assertNotNull(clearedCart);
		assertEquals(0, clearedCart.getInventoryItems().size());

	}
}
