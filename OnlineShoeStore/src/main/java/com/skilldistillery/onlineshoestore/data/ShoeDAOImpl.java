package com.skilldistillery.onlineshoestore.data;

<<<<<<< HEAD
import java.util.Collections;
import java.util.List;

=======
>>>>>>> Remote_Branch1
import org.springframework.stereotype.Service;

import com.skilldistillery.jpaonlineshoestore.entities.Shoe;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ShoeDAOImpl implements ShoeDAO {
<<<<<<< HEAD

=======
	
>>>>>>> Remote_Branch1
	@PersistenceContext
	private EntityManager em;

	@Override
	public Shoe addShoe(Shoe shoe) {
<<<<<<< HEAD
		em.persist(shoe);
		return shoe;
	}

	@Override
	public boolean deleteShoe(Shoe shoe) {
		boolean wasDeleted = false;
		Shoe deleteShoe = em.find(Shoe.class, shoe);
		if (deleteShoe != null) {
			em.remove(deleteShoe);
			wasDeleted = true;
		}
		wasDeleted = wasDeleted && !em.contains(deleteShoe);
		return wasDeleted;
=======
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shoe deleteShoe(Shoe shoe) {
		// TODO Auto-generated method stub
		return null;
>>>>>>> Remote_Branch1
	}

	@Override
	public Shoe updateShoe(Shoe shoe) {
<<<<<<< HEAD
		Shoe newShoe = em.find(Shoe.class, shoe.getId());
	    if (newShoe == null) {
	        return null;
	    }
	    if (shoe.getBrand() != null) {
	        newShoe.setBrand(shoe.getBrand());
	    }
	    if (shoe.getType() != null) {
	        newShoe.setType(shoe.getType());
	    }
	    
	    em.merge(newShoe);
	    return newShoe;
	}

	@Override
	public List<Shoe> findShoeByKeyword(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			return Collections.emptyList();
		}
		String jpql = "SELECT s FROM Shoe s " +
                "WHERE LOWER(COALESCE(s.brand.name, '')) LIKE :kw " +
                "OR LOWER(COALESCE(s.type.name, '')) LIKE :kw";
		
		return em.createQuery(jpql, Shoe.class)
				.setParameter("kw", "%" + keyword.toLowerCase() + "%")
				.getResultList();
	}
=======
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shoe findShoeByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

>>>>>>> Remote_Branch1
	
}
