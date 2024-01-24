package com.example.maintainer.converter.impl;

import org.springframework.stereotype.Component;

import com.example.maintainer.converter.UserConverter;
import com.example.maintainer.entity.User;

@Component
public class UserConverterImpl implements UserConverter {
	
	@Override
	public com.example.maintainer.model.User convertEntity(User user) {		
		com.example.maintainer.model.User retUser = new com.example.maintainer.model.User();
		retUser.setId(user.getId());
		retUser.setName(user.getName());
		
		return retUser;
	}
	
	@Override
	public User convertModel(com.example.maintainer.model.User user) {
		User retUser = new User();
		retUser.setId(user.getId());
		retUser.setName(user.getName());
		
		return retUser;
	}
	
}
