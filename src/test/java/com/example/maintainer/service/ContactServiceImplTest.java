package com.example.maintainer.service;

import com.example.maintainer.dao.ContactDao;
import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.Contact;
import com.example.maintainer.service.impl.ContactServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class ContactServiceImplTest {

    @InjectMocks
    ContactServiceImpl contactServiceImpl;

    @Mock
    ContactDao contactDao;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListContacts() {
        List<Contact> listContacts = new ArrayList<>();
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

        Contact contactModel = new Contact();
        contactModel.setId(1L);
        contactModel.setAddress(addressModel);
        contactModel.setEmail("teszt@gmail.com");
        contactModel.setPhoneNumber("+36301234567");
        listContacts.add(contactModel);

        when(contactDao.listContacts()).thenReturn(listContacts);

        List<Contact> retList = contactServiceImpl.listContacts();

        verify(contactDao, times(1)).listContacts();
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

        Contact contactModel = new Contact();
        contactModel.setAddress(addressModel);
        contactModel.setEmail("teszt@gmail.com");
        contactModel.setPhoneNumber("+36301234567");

        when(contactDao.saveOrUpdate(contactModel)).thenReturn(1L);
        when(contactDao.findById(1L)).thenReturn(contactModel);

        Contact retContactModel = contactServiceImpl.createContact(contactModel);

        verify(contactDao, times(1)).saveOrUpdate(contactModel);
        verify(contactDao, times(1)).findById(1L);
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

        Contact contactModel = new Contact();
        contactModel.setId(1L);
        contactModel.setAddress(addressModel);
        contactModel.setEmail("teszt@gmail.com");
        contactModel.setPhoneNumber("+36301234567");

        when(contactDao.existsById(1L)).thenReturn(true);
        when(contactDao.saveOrUpdate(contactModel)).thenReturn(1L);
        when(contactDao.findById(1L)).thenReturn(contactModel);

        Contact retContactModel = contactServiceImpl.modifyContactById(1L, contactModel);

        verify(contactDao, times(1)).existsById(1L);
        verify(contactDao, times(1)).saveOrUpdate(contactModel);
        verify(contactDao, times(1)).findById(1L);
        assertNotNull(retContactModel);
        assertEquals(contactModel, retContactModel);
    }

    @Test
    void testDeleteContactById() throws MaintainerException {
        when(contactDao.existsById(1L)).thenReturn(true);

        contactServiceImpl.deleteContactById(1L);

        verify(contactDao, times(1)).deleteById(1L);
    }

}
