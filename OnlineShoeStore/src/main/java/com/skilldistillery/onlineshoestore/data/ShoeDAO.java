package com.skilldistillery.onlineshoestore.data;

import java.util.List;

import com.skilldistillery.jpaonlineshoestore.entities.Shoe;

public interface ShoeDAO {
	Shoe addShoe(Shoe shoe);
	boolean deleteShoe(Shoe shoe);
	Shoe updateShoe(Shoe shoe);
	List<Shoe> findShoeByKeyword(String keyword);
	Shoe findShoeById(int id);
	
  
}
