package com.skilldistillery.onlineshoestore.data;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.skilldistillery.jpaonlineshoestore.entities.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserDAOImpl implements UserDAO {
	@PersistenceContext
	private EntityManager em;
	User updateuser = new User();
	User newUser = new User();

	@Override
	public User addUser(User user) {
		user.setEnabled(true);
		em.persist(user);
		newUser = em.find(User.class, user.getId());
		return user;
	}

	@Override
	public boolean deleteUser(User user) {
		boolean wasDeleted = false;

		User userToBeDeleted = em.find(User.class, user.getId());
		if (userToBeDeleted != null) {
			em.remove(userToBeDeleted);
		wasDeleted = true;
		}
		wasDeleted = !em.contains(userToBeDeleted);
		return wasDeleted;
	}

	@Override 
	public User updateUser(int id, User user) {
		User managedUser = em.find(User.class, id);
		managedUser.setUsername(user.getUsername());
		managedUser.setPassword(user.getPassword());
		managedUser.setEnabled(user.getEnabled());
		managedUser.setRole(user.getRole());
		return managedUser;

	}

	@Override
	public User findUserById(int id) {
		User user = em.find(User.class, id);
		
		return user;
	}

	 @Override
	    public User findByUsernameAndPassword(String username, String password) {
	        String jpql = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password";
	        try {
	            return em.createQuery(jpql, User.class)
	                     .setParameter("username", username)
	                     .setParameter("password", password)
	                     .getSingleResult();
	        } catch (NoResultException e) {
	            return null; // Return null if no user matches the credentials
	        }
	    }
	
	
}