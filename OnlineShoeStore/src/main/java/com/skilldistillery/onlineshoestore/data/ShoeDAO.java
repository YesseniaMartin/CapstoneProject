package com.skilldistillery.onlineshoestore.data;

import com.skilldistillery.jpaonlineshoestore.entities.Shoe;

public interface ShoeDAO {
	Shoe addShoe(Shoe shoe);
	Shoe deleteShoe(Shoe shoe);
	Shoe updateShoe(Shoe shoe);
	Shoe findShoeByKeyword(String keyword);

}
