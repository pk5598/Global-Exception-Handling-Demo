package com.webservices.restwebservices.user.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.webservices.restwebservices.user.User;

import net.bytebuddy.asm.Advice.This;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();

	private static int usersCount = 3;

	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Eve", new Date()));
		users.add(new User(3, "Jack", new Date()));
	}
	
	public List<User> findAll() {
		return this.users;
	}

	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}

	public User findOne(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteById(int id) {
		boolean id_val=false;
		for (User user : users) {
			if (user.getId() == id) {
			id_val=true;
			break;
			}
		}
		if (id_val) {
			User user=this.findOne(id);
			this.users.remove(user);
			return user;
			
		}
		return null;
	}
	
	
	
}
