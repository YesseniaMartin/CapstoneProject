package com.skilldistillery.onlineshoestore.data;

import com.skilldistillery.jpaonlineshoestore.entities.Cart;
import com.skilldistillery.jpaonlineshoestore.entities.Shoe;

/**
 * Interface to 
 */
public interface CartDAO {
	
	/**
     * Adds a shoe to the cart dynamically for a specific cart ID.
     *
     * @param cartId The ID of the cart to add the shoe to.
     * @param shoe   The shoe to be added to the cart.
     * @return The updated Cart object after the shoe has been added.
     */
	Cart addShoeToCart(int cartId, Shoe shoe); 
	
	/**
     * Removes a specific shoe from the cart dynamically for a given cart ID.
     *
     * @param cartId The ID of the cart to remove the shoe from.
     * @param shoe   The shoe to be removed from the cart.
     * @return true if the shoe was successfully removed, false otherwise.
     */
    boolean removeShoeFromCart(int cartId, Shoe shoe); 
    
    /**
     * Retrieves a cart by its unique ID, including all items in the cart.
     *
     * @param cartId The unique identifier of the cart to retrieve.
     * @return The Cart object if found, or null if no cart with the given ID exists.
     */
    Cart getCartById(int cartId);  
    
    /**
     * Clears all items from the cart dynamically for a given cart ID.
     *
     * @param cartId The unique identifier of the cart to clear.
     * @return The updated Cart object after clearing all items.
     */
    Cart clearCart(int cartId);

	Cart findCartByCustomerId(int customerId);  
	
	Cart createCart(Cart cart);

	Cart updateCart(Cart cart);
    
    

}
