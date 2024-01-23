package com.example.maintainer.converter;

import org.springframework.stereotype.Component;

import com.example.maintainer.entity.User;

@Component
public class UserConverter {
	
	public com.example.maintainer.model.User convertEntity(User user) {		
		com.example.maintainer.model.User retUser = new com.example.maintainer.model.User();
		retUser.setId(user.getId());
		retUser.setName(user.getName());
		
		return retUser;
	}
	
	public User convertModel(com.example.maintainer.model.User user) {
		User retUser = new User();
		retUser.setId(user.getId());
		retUser.setName(user.getName());
		
		return retUser;
	}
	
}
