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

import com.example.maintainer.converter.AddressConverter;
import com.example.maintainer.dao.AddressDao;
import com.example.maintainer.dao.ContactDao;
import com.example.maintainer.entity.Address;
import com.example.maintainer.entity.User;
import com.example.maintainer.entity.enumeration.AddressType;
import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.service.impl.AddressServiceImpl;

@ExtendWith({ MockitoExtension.class })
public class AddressServiceImplTest {

	@InjectMocks
	AddressServiceImpl addressServiceImpl;

	@Mock
	AddressDao addressDao;

	@Mock
	ContactDao contactDao;

	@Mock
	AddressConverter addressConverter;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testListAddresses() {
		List<Address> listEntities = new ArrayList<>();
		User userEntity = new User();
		userEntity.setId(1L);
		userEntity.setName("Hivekovics Zoltán");

		Address addressEntity = new Address();
		addressEntity.setId(1L);
		addressEntity.setUser(userEntity);
		addressEntity.setAddressType(AddressType.PERMANET);
		addressEntity.setCountry("Magyarország");
		addressEntity.setCity("Budapest");
		addressEntity.setPlaceName("Fő utca");
		addressEntity.setStreetNumber("1");
		listEntities.add(addressEntity);

		com.example.maintainer.model.User userModel = new com.example.maintainer.model.User();
		userModel.setId(1L);
		userModel.setName("Hivekovics Zoltán");

		com.example.maintainer.model.Address addressModel = new com.example.maintainer.model.Address();
		addressModel.setId(1L);
		addressModel.setUser(userModel);
		addressModel.setAddressType(com.example.maintainer.model.enumeration.AddressType.PERMANET);
		addressModel.setCountry("Magyarország");
		addressModel.setCity("Budapest");
		addressModel.setPlaceName("Fő utca");
		addressModel.setStreetNumber("1");

		when(addressDao.findAll()).thenReturn(listEntities);
		when(addressConverter.convertEntity(addressEntity)).thenReturn(addressModel);

		List<com.example.maintainer.model.Address> retList = addressServiceImpl.listAddresses();

		verify(addressDao, times(1)).findAll();
		verify(addressConverter, times(1)).convertEntity(addressEntity);
		assertEquals(1, retList.size());
	}

	@Test
	void testCreateAddress() throws MaintainerException {
		com.example.maintainer.model.User userModel = new com.example.maintainer.model.User();
		userModel.setId(1L);
		userModel.setName("Hivekovics Zoltán");

		com.example.maintainer.model.Address addressModel = new com.example.maintainer.model.Address();
		addressModel.setUser(userModel);
		addressModel.setAddressType(com.example.maintainer.model.enumeration.AddressType.PERMANET);
		addressModel.setCountry("Magyarország");
		addressModel.setCity("Budapest");
		addressModel.setPlaceName("Fő utca");
		addressModel.setStreetNumber("1");

		User userEntity = new User();
		userEntity.setId(1L);
		userEntity.setName("Hivekovics Zoltán");

		Address addressEntity = new Address();
		addressEntity.setUser(userEntity);
		addressEntity.setAddressType(AddressType.PERMANET);
		addressEntity.setCountry("Magyarország");
		addressEntity.setCity("Budapest");
		addressEntity.setPlaceName("Fő utca");
		addressEntity.setStreetNumber("1");

		when(addressDao.saveAndFlush(addressEntity)).thenReturn(addressEntity);
		when(addressConverter.convertEntity(addressEntity)).thenReturn(addressModel);
		when(addressConverter.convertModel(addressModel)).thenReturn(addressEntity);

		com.example.maintainer.model.Address retAddressModel = addressServiceImpl.createAddress(addressModel);

		verify(addressDao, times(1)).saveAndFlush(addressEntity);
		verify(addressConverter, times(1)).convertModel(addressModel);
		verify(addressConverter, times(1)).convertEntity(addressEntity);
		assertNotNull(retAddressModel);
		assertEquals(addressModel, retAddressModel);
	}

	@Test	
	void testModifyAddressById() throws MaintainerException {
		com.example.maintainer.model.User userModel = new com.example.maintainer.model.User();
		userModel.setId(1L);
		userModel.setName("Hivekovics Zoltán");

		com.example.maintainer.model.Address addressModel = new com.example.maintainer.model.Address();
		addressModel.setId(1L);
		addressModel.setUser(userModel);
		addressModel.setAddressType(com.example.maintainer.model.enumeration.AddressType.PERMANET);
		addressModel.setCountry("Magyarország");
		addressModel.setCity("Budapest");
		addressModel.setPlaceName("Fő utca");
		addressModel.setStreetNumber("1");

		User userEntity = new User();
		userEntity.setId(1L);
		userEntity.setName("Hivekovics Zoltán");

		Address addressEntity = new Address();
		addressEntity.setId(1L);
		addressEntity.setUser(userEntity);
		addressEntity.setAddressType(AddressType.PERMANET);
		addressEntity.setCountry("Magyarország");
		addressEntity.setCity("Budapest");
		addressEntity.setPlaceName("Fő utca");
		addressEntity.setStreetNumber("1");

		when(addressDao.existsById(1L)).thenReturn(true);
		when(addressDao.saveAndFlush(addressEntity)).thenReturn(addressEntity);
		when(addressConverter.convertEntity(addressEntity)).thenReturn(addressModel);
		when(addressConverter.convertModel(addressModel)).thenReturn(addressEntity);

		com.example.maintainer.model.Address retAddressModel = addressServiceImpl.modifyAddressById(1L, addressModel);

		verify(addressDao, times(1)).existsById(1L);
		verify(addressDao, times(1)).saveAndFlush(addressEntity);
		verify(addressConverter, times(1)).convertModel(addressModel);
		verify(addressConverter, times(1)).convertEntity(addressEntity);
		assertNotNull(retAddressModel);
		assertEquals(addressModel, retAddressModel);
	}

	@Test
	void testDeleteAddressById() throws MaintainerException {
		when(contactDao.countByAddressId(1L)).thenReturn(0);

		addressServiceImpl.deleteAddressById(1L);

		verify(addressDao, times(1)).deleteById(1L);
		verify(addressDao, times(1)).flush();
	}

}
