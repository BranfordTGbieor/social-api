package com.taylietech.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDAO {

	private static List<User> users = new ArrayList<>();

	private static int userIdCount = 3;

	static {

		users.add(new User(1, "Branford", new Date()));
		users.add(new User(2, "Matilda", new Date()));
		users.add(new User(3, "Lisa", new Date()));
	}

	// method to return the list of all users
	public List<User> findAll() {
		return users;
	}

	// method to add a new user
	public User saveUser(User user) {

		if (user.getId() == 0) {
			user.setId(++userIdCount);
		}

		users.add(user);

		return user;
	}

	// method to return a specific user
	public User findOne(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}

		return null;
	}

	// method to delete a specific user
	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
					iterator.remove();
					return user;
				}
			}

		return null;
	}

}
