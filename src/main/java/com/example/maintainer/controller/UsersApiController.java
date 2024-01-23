package com.example.maintainer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.User;
import com.example.maintainer.service.UserService;

@RestController
public class UsersApiController implements UsersApi {

	@Autowired
	UserService userService;

	@Override
	@GetMapping(value = "/users", produces = { "application/json" })
	@ResponseBody
	public List<User> listUsers() {
		return userService.listUsers();
	}

	@Override
	@PostMapping(value = "/user", produces = { "application/json" }, consumes = { "application/json" })
	public User createUser(@RequestBody User user) throws MaintainerException {
		return userService.createUser(user);
	}

	@Override
	@PutMapping(value = "/user/{id}", produces = { "application/json" }, consumes = { "application/json" })
	public User modifyUserById(@PathVariable("id") Long id, @RequestBody User user) throws MaintainerException {
		return userService.modifyUserById(id, user);
	}

	@Override
	@DeleteMapping(value = "/user/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}

}
