package com.skilldistillery.onlineshoestore.data;

import java.util.List;

import com.skilldistillery.jpaonlineshoestore.entities.Shoe;

public interface ShoeDAO {
	Shoe addShoe(Shoe shoe);
	public boolean deleteShoe(Shoe shoe);
	Shoe updateShoe(Shoe shoe);
	public List<Shoe> findShoeByKeyword(String keyword);

}
