package com.example.maintainer.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.maintainer.converter.UserConverter;
import com.example.maintainer.dao.AddressDao;
import com.example.maintainer.dao.UserDao;
import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.User;
import com.example.maintainer.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	AddressDao addressDao; 

	@Autowired
	UserConverter userConverter;

	@Override
	public List<User> listUsers() {
		return userDao.findAll().stream().map(user -> userConverter.convertEntity(user)).collect(Collectors.toList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public User createUser(User user) throws MaintainerException {
		invalidUserId(user);
		com.example.maintainer.entity.User entityUser = userDao.saveAndFlush(userConverter.convertModel(user));
		return userConverter.convertEntity(entityUser);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public User modifyUserById(Long id, User user) throws MaintainerException {
		validateUserId(id, user);
		if (userDao.existsById(id)) {
			com.example.maintainer.entity.User entityUser = userDao.saveAndFlush(userConverter.convertModel(user));
			return userConverter.convertEntity(entityUser);
		}
		return null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteUserById(Long id) throws MaintainerException {
		validateAddress(id);
		userDao.deleteById(id);
		userDao.flush();
	}

	private void invalidUserId(User user) throws MaintainerException {
		if (user.getId() != null) {
			throw new MaintainerException("Invalid user model");
		}
	}

	private void validateUserId(Long id, User user) throws MaintainerException {
		if (!id.equals(user.getId())) {
			throw new MaintainerException("Invalid user model");
		}
	}

	private void validateAddress(Long id) throws MaintainerException {		
		if (addressDao.countByUserId(id) > 0) {
			throw new MaintainerException("Unable to delete - has contact data");
		}
	}

}
