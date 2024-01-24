package com.example.maintainer.converter;

import com.example.maintainer.entity.User;

public interface UserConverter {

	com.example.maintainer.model.User convertEntity(User user);

	User convertModel(com.example.maintainer.model.User user);

}
