package com.example.maintainer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.maintainer.model.User;
import com.example.maintainer.service.UserService;

@RestController
public class UsersApiController implements UsersApi {

	@Autowired
	UserService userService;

	@Override		
	@GetMapping(value = "/users", produces = {"application/json"})
	@ResponseBody
	public List<User> listUsers() {
		return userService.listUsers();
	}

	@Override
	@PostMapping(value = "/user", produces = {"application/json"}, consumes = {"application/json"})
	public User createUser(@RequestBody User user) throws Exception {
		System.out.println(user);
		return userService.createUser(user);
	}

}
