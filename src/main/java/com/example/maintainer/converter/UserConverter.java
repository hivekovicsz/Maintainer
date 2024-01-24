package com.example.maintainer.converter;

import com.example.maintainer.model.User;

public interface UserConverter {

	User convertEntity(com.example.maintainer.entity.User user);

	com.example.maintainer.entity.User convertModel(User user);

}
