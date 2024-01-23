package com.example.maintainer.controller;

import java.util.List;

import com.example.maintainer.model.User;

public interface UsersApi {

	List<User> listUsers();
	
	User createUser(User user) throws Exception;
}
