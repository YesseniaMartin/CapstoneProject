package com.skilldistillery.onlineshoestore.data;

import com.skilldistillery.jpaonlineshoestore.entities.*;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
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
		em.persist(user);
		newUser = em.find(User.class, user.getId());
		return user;
	}

	@Override
	public boolean deleteUser(User user) {
		boolean wasDeleted = false;

		User userToBeDeleted = em.find(User.class, user);
		if (userToBeDeleted != null)
			em.remove(userToBeDeleted);
		wasDeleted = true;

		wasDeleted = !em.contains(userToBeDeleted);
		return wasDeleted;
	}

	@Override /*make sure to test */
	public User updateUser(User user) {
		updateuser = null;
		String jpql = "UPDATE user user " + "SET user.id = :id " + "SET user.firstname = :firstname "
				+ "SET user.lastname = :lastname " + "SET user.email = :email" + "SET user.phone = :phone "
				+ "SET user.user = :user ";

		User updatedUser = em.find(User.class, user.getId());

		if (user.getUsername() == null) {
			user.setUsername(updatedUser.getUsername());
		}
		if (user.getPassword() == null) {
			user.setPassword(updatedUser.getPassword());
		}
		if (user.getEnabled() == false) {
			user.setEnabled(true);
		}
		if (user.getRole() == null) {
			user.setRole(updatedUser.getRole());
		}
		
		int updateColumns = em.createQuery(jpql).setParameter("username", user.getUsername())
				.setParameter("passwrd", user.getPassword()).setParameter("enabled", user.getEnabled())
				.setParameter("role", user.getRole()).executeUpdate();

		if (updateColumns > 0) {
			return updatedUser;
		} else {

			return updateuser;
		}
	}

	@Override
	public User findUserById(int id) {
		User user = em.find(User.class, id);
		
		return user;
	}

}