package com.skilldistillery.onlineshoestore.data;

import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

import com.skilldistillery.jpaonlineshoestore.entities.CustomerOrder;
import com.skilldistillery.jpaonlineshoestore.entities.Shoe;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ShoeDAOImpl implements ShoeDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Shoe addShoe(Shoe shoe) {
		em.persist(shoe);
		return shoe;
	}

	@Override
	public boolean deleteShoe(Shoe shoe) {
		boolean wasDeleted = false;
		Shoe deleteShoe = em.find(Shoe.class, shoe.getId());
		if (deleteShoe != null) {
			em.remove(deleteShoe);
			wasDeleted = true;
		}
		wasDeleted = wasDeleted && !em.contains(deleteShoe);
		return wasDeleted;
	}

	@Override
	public Shoe updateShoe(Shoe shoe) {
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
		 String query = "SELECT s FROM Shoe s WHERE s.brand.name LIKE :keyword OR s.description LIKE :keyword";
		    return em.createQuery(query, Shoe.class)
		             .setParameter("keyword", "%" + keyword + "%")
		             .getResultList();
		}

	@Override
	public List<Shoe> findAll() {
		String jpql = "SELECT s FROM Shoe s";
        return em.createQuery(jpql, Shoe.class).getResultList();
    
	}

	@Override
	public Shoe findShoeById(int id) {
		Shoe shoe = em.find(Shoe.class, id);

		return shoe;
	}
}
