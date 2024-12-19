package com.skilldistillery.onlineshoestore.data;

import com.skilldistillery.jpaonlineshoestore.entities.User;

public interface UserDAO {
	User addUser(User user);
	boolean deleteUser(User user);
	User updateUser(int id, User user);
	User findUserById(int id);
	User findByUsernameAndPassword(String username, String password);
}
