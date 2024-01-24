package com.example.maintainer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.maintainer.converter.UserConverter;
import com.example.maintainer.dao.AddressDao;
import com.example.maintainer.dao.UserDao;
import com.example.maintainer.entity.User;
import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.service.impl.UserServiceImpl;

@ExtendWith({ MockitoExtension.class })
public class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserDao userDao;

	@Mock
	AddressDao addressDao;

	@Mock
	UserConverter userConverter;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testListUsers() {
		List<User> listEntities = new ArrayList<>();
		User userEntity = new User();
		userEntity.setId(1L);
		userEntity.setName("Hivekovics Zoltán");
		listEntities.add(userEntity);

		com.example.maintainer.model.User userModel = new com.example.maintainer.model.User();
		userModel.setId(1L);
		userModel.setName("Hivekovics Zoltán");

		when(userDao.findAll()).thenReturn(listEntities);
		when(userConverter.convertEntity(userEntity)).thenReturn(userModel);

		List<com.example.maintainer.model.User> retList = userServiceImpl.listUsers();

		verify(userDao, times(1)).findAll();
		verify(userConverter, times(1)).convertEntity(userEntity);
		assertEquals(1, retList.size());
	}

	@Test
	void testCreateUser() throws MaintainerException {
		com.example.maintainer.model.User userModel = new com.example.maintainer.model.User();
		userModel.setName("Hivekovics Zoltán");

		User userEntity = new User();
		userEntity.setName("Hivekovics Zoltán");

		when(userDao.saveAndFlush(userEntity)).thenReturn(userEntity);
		when(userConverter.convertEntity(userEntity)).thenReturn(userModel);
		when(userConverter.convertModel(userModel)).thenReturn(userEntity);

		com.example.maintainer.model.User retUserModel = userServiceImpl.createUser(userModel);

		verify(userDao, times(1)).saveAndFlush(userEntity);
		verify(userConverter, times(1)).convertModel(userModel);
		verify(userConverter, times(1)).convertEntity(userEntity);
		assertNotNull(retUserModel);
		assertEquals(userModel, retUserModel);
	}
	
	@Test
	void testModifyUserById() throws MaintainerException {
		com.example.maintainer.model.User userModel = new com.example.maintainer.model.User();
		userModel.setId(1L);
		userModel.setName("Hivekovics Zoltán");

		User userEntity = new User();
		userEntity.setId(1L);
		userEntity.setName("Hivekovics Zoltán");

		when(userDao.existsById(1L)).thenReturn(true);
		when(userDao.saveAndFlush(userEntity)).thenReturn(userEntity);		
		when(userConverter.convertEntity(userEntity)).thenReturn(userModel);
		when(userConverter.convertModel(userModel)).thenReturn(userEntity);

		com.example.maintainer.model.User retUserModel = userServiceImpl.modifyUserById(1L, userModel);

		verify(userDao, times(1)).existsById(1L);
		verify(userDao, times(1)).saveAndFlush(userEntity);
		verify(userConverter, times(1)).convertModel(userModel);
		verify(userConverter, times(1)).convertEntity(userEntity);
		assertNotNull(retUserModel);
		assertEquals(userModel, retUserModel);
	}
	
	@Test
	void testDeleteUserById() throws MaintainerException {
		when(addressDao.countByUserId(1L)).thenReturn(0);

		userServiceImpl.deleteUserById(1L);

		verify(userDao, times(1)).deleteById(1L);
		verify(userDao, times(1)).flush();
	}

}
