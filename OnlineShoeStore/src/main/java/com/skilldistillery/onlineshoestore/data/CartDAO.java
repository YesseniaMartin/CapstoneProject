package com.skilldistillery.onlineshoestore.data;

import com.skilldistillery.jpaonlineshoestore.entities.Cart;
import com.skilldistillery.jpaonlineshoestore.entities.Shoe;

public interface CartDAO {
	Cart addCart(Shoe shoe);
	Cart deleteCart(Shoe shoe);
	Cart updateCart(Shoe shoe);

}
