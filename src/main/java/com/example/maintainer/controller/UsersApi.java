package com.example.maintainer.controller;

import java.util.List;

import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.User;

public interface UsersApi {

	List<User> listUsers();

	User createUser(User user) throws MaintainerException;

	User modifyUserById(Long id, User user) throws MaintainerException;

	void deleteUserById(Long id);

}
