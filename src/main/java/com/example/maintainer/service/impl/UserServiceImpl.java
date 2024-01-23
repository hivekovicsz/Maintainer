package com.example.maintainer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.maintainer.converter.UserConverter;
import com.example.maintainer.dao.UserDao;
import com.example.maintainer.model.User;
import com.example.maintainer.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	UserConverter userConverter;

	@Override
	public List<User> listUsers() {
		return userDao.findAllUsers();
	}

	@Override
	public User createUser(User user) throws Exception {
		validateUserId(user);
		System.out.println(user);
		com.example.maintainer.entity.User entityUser = userDao.save(userConverter.convertModel(user));
		return userConverter.convertEntity(entityUser);
	}

	private void validateUserId(User user) throws Exception {
		if (user.getId() != null) {
			throw new Exception("Invalid user model");
		}
		
	}

}
