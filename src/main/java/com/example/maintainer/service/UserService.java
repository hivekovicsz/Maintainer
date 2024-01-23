package com.example.maintainer.service;

import java.util.List;

import com.example.maintainer.model.User;

public interface UserService {

	List<User> listUsers();

	User createUser(User user) throws Exception;

}
