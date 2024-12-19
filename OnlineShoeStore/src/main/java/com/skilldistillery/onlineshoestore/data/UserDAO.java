package com.skilldistillery.onlineshoestore.data;

import com.skilldistillery.jpaonlineshoestore.entities.User;

public interface UserDAO {
	User addUser(User user);
<<<<<<< HEAD
	User deleteUser(User user);
=======
	boolean deleteUser(User user);
>>>>>>> Remote_Branch1
	User updateUser(User user);
	User findUserById(int id);

}
