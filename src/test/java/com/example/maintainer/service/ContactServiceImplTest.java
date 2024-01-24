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

import com.example.maintainer.converter.ContactConverter;
import com.example.maintainer.dao.ContactDao;
import com.example.maintainer.entity.Address;
import com.example.maintainer.entity.Contact;
import com.example.maintainer.entity.User;
import com.example.maintainer.entity.enumeration.AddressType;
import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.service.impl.ContactServiceImpl;

@ExtendWith({ MockitoExtension.class })
public class ContactServiceImplTest {

	@InjectMocks
	ContactServiceImpl contactServiceImpl;

	@Mock
	ContactDao contactDao;

	@Mock
	ContactConverter contactConverter;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testListContacts() {
		List<Contact> listEntities = new ArrayList<>();
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

		Contact contactEntity = new Contact();
		contactEntity.setId(1L);
		contactEntity.setAddress(addressEntity);
		contactEntity.setEmail("teszt@gmail.com");
		contactEntity.setPhoneNumber("+36301234567");
		listEntities.add(contactEntity);

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

		com.example.maintainer.model.Contact contactModel = new com.example.maintainer.model.Contact();
		contactModel.setId(1L);
		contactModel.setAddress(addressModel);
		contactModel.setEmail("teszt@gmail.com");
		contactModel.setPhoneNumber("+36301234567");

		when(contactDao.findAll()).thenReturn(listEntities);
		when(contactConverter.convertEntity(contactEntity)).thenReturn(contactModel);

		List<com.example.maintainer.model.Contact> retList = contactServiceImpl.listContacts();

		verify(contactDao, times(1)).findAll();
		verify(contactConverter, times(1)).convertEntity(contactEntity);
		assertEquals(1, retList.size());
	}

	@Test
	void testCreateContact() throws MaintainerException {
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

		com.example.maintainer.model.Contact contactModel = new com.example.maintainer.model.Contact();
		contactModel.setAddress(addressModel);
		contactModel.setEmail("teszt@gmail.com");
		contactModel.setPhoneNumber("+36301234567");

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

		Contact contactEntity = new Contact();
		contactEntity.setId(1L);
		contactEntity.setAddress(addressEntity);
		contactEntity.setEmail("teszt@gmail.com");
		contactEntity.setPhoneNumber("+36301234567");

		when(contactDao.saveAndFlush(contactEntity)).thenReturn(contactEntity);
		when(contactConverter.convertEntity(contactEntity)).thenReturn(contactModel);
		when(contactConverter.convertModel(contactModel)).thenReturn(contactEntity);

		com.example.maintainer.model.Contact retContactModel = contactServiceImpl.createContact(contactModel);

		verify(contactDao, times(1)).saveAndFlush(contactEntity);
		verify(contactConverter, times(1)).convertModel(contactModel);
		verify(contactConverter, times(1)).convertEntity(contactEntity);
		assertNotNull(retContactModel);
		assertEquals(contactModel, retContactModel);
	}

	@Test
	void testModifyContactById() throws MaintainerException {
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

		com.example.maintainer.model.Contact contactModel = new com.example.maintainer.model.Contact();
		contactModel.setId(1L);
		contactModel.setAddress(addressModel);
		contactModel.setEmail("teszt@gmail.com");
		contactModel.setPhoneNumber("+36301234567");

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

		Contact contactEntity = new Contact();
		contactEntity.setId(1L);
		contactEntity.setAddress(addressEntity);
		contactEntity.setEmail("teszt@gmail.com");
		contactEntity.setPhoneNumber("+36301234567");

		when(contactDao.existsById(1L)).thenReturn(true);
		when(contactDao.saveAndFlush(contactEntity)).thenReturn(contactEntity);
		when(contactConverter.convertEntity(contactEntity)).thenReturn(contactModel);
		when(contactConverter.convertModel(contactModel)).thenReturn(contactEntity);

		com.example.maintainer.model.Contact retContactModel = contactServiceImpl.modifyContactById(1L, contactModel);

		verify(contactDao, times(1)).existsById(1L);
		verify(contactDao, times(1)).saveAndFlush(contactEntity);
		verify(contactConverter, times(1)).convertModel(contactModel);
		verify(contactConverter, times(1)).convertEntity(contactEntity);
		assertNotNull(retContactModel);
		assertEquals(contactModel, retContactModel);
	}

	@Test
	void testDeleteContactById() throws MaintainerException {

		contactServiceImpl.deleteContactById(1L);

		verify(contactDao, times(1)).deleteById(1L);
		verify(contactDao, times(1)).flush();
	}

}
