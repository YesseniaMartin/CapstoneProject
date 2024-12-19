package com.skilldistillery.onlineshoestore.data;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> Remote_Branch1
import com.skilldistillery.jpaonlineshoestore.entities.Shoe;

public interface ShoeDAO {
	Shoe addShoe(Shoe shoe);
<<<<<<< HEAD
	public boolean deleteShoe(Shoe shoe);
	Shoe updateShoe(Shoe shoe);
	public List<Shoe> findShoeByKeyword(String keyword);
=======
	Shoe deleteShoe(Shoe shoe);
	Shoe updateShoe(Shoe shoe);
	Shoe findShoeByKeyword(String keyword);
>>>>>>> Remote_Branch1

}
