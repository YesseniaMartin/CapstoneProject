package com.skilldistillery.onlineshoestore.data;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.jpaonlineshoestore.entities.Cart;
import com.skilldistillery.jpaonlineshoestore.entities.InventoryItem;
import com.skilldistillery.jpaonlineshoestore.entities.Shoe;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartDAOImpl implements CartDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Cart addShoeToCart(int cartId, Shoe shoe) {
		Cart cart = em.find(Cart.class, cartId); // Retrieve the cart
		if (cart == null) {
			cart = new Cart();
			em.persist(cart); // Create a new cart if it doesn't exist
		}

		Shoe managedShoe = em.find(Shoe.class, shoe.getId()); // Retrieve the shoe

		if (managedShoe != null) {
			// Create a new inventory item
			InventoryItem item = new InventoryItem();
			item.setShoeId(managedShoe);

			// Add the inventory item to the cart
			cart.addInventoryItem(item);
			item.addCart(cart); // Manage both sides of the relationship

			em.persist(item); // Persist the new inventory item
			em.merge(cart); // Update the cart
		}

		return cart;
	}

	@Override
	public boolean removeShoeFromCart(int cartId, Shoe shoe) {
		Cart cart = em.find(Cart.class, cartId); // Retrieve the cart
		if (cart == null) {
			return false; // Cart does not exist
		}

		// Locate the inventory item corresponding to the shoe
		InventoryItem itemToRemove = cart.getInventoryItems().stream().filter(item -> item.getShoeId().equals(shoe))
				.findFirst().orElse(null);

		if (itemToRemove != null) {
			cart.removeInventoryItem(itemToRemove);
			itemToRemove.removeCart(cart); // Manage both sides of the relationship
			em.remove(itemToRemove); // Remove the item
			em.merge(cart); // Update the cart
			return true;
		}

		return false; // Shoe not found in the cart
	}

	@Override
	public Cart getCartById(int cartId) {
		return em.find(Cart.class, cartId);
	}

	@Override
	public Cart clearCart(int cartId) {
		Cart cart = em.find(Cart.class, cartId); // Retrieve the cart
		if (cart == null) {
			return null; // Cart does not exist
		}

		List<InventoryItem> items = cart.getInventoryItems();

		// Remove each inventory item
		for (InventoryItem item : items) {
			item.removeCart(cart); // Manage both sides of the relationship
			em.remove(item);
		}

		cart.getInventoryItems().clear(); // Clear the list in the cart
		em.merge(cart); // Update the cart

		return cart;
	}

	@Override
	public Cart findCartByCustomerId(int customerId) {
		String query = "SELECT DISTINCT c FROM Cart c " + "JOIN FETCH c.inventoryItems i " + "JOIN FETCH i.shoeId s "
				+ "WHERE c.customer.id = :cid";
		Cart cart = null;
		cart = em.createQuery(query, Cart.class).setParameter("cid", customerId).getSingleResult();

		return cart;

	}

	@Override
	public Cart createCart(Cart cart) {
		em.persist(cart);

		return cart;
	}
	
	@Override
    public Cart updateCart(Cart cart) {
        return em.merge(cart);
    }

}
