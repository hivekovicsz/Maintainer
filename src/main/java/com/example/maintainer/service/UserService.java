package com.example.maintainer.service;

import java.util.List;

import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.User;

public interface UserService {

	List<User> listUsers();

	User getUserById(Long id);

	User createUser(User user) throws MaintainerException;

	User modifyUserById(Long id, User user) throws MaintainerException;

	void deleteUserById(Long id) throws MaintainerException;

}
