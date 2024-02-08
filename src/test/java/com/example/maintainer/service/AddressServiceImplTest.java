package com.example.maintainer.service;

import com.example.maintainer.dao.AddressDao;
import com.example.maintainer.dao.ContactDao;
import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.Address;
import com.example.maintainer.service.impl.AddressServiceImpl;
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
public class AddressServiceImplTest {

    @InjectMocks
    AddressServiceImpl addressServiceImpl;

    @Mock
    AddressDao addressDao;

    @Mock
    ContactDao contactDao;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListAddresses() {
        List<Address> listModels = new ArrayList<>();
        com.example.maintainer.model.User userModel = new com.example.maintainer.model.User();
        userModel.setId(1L);
        userModel.setName("Hivekovics Zoltán");

        Address addressModel = new Address();
        addressModel.setId(1L);
        addressModel.setUser(userModel);
        addressModel.setAddressType(com.example.maintainer.model.enumeration.AddressType.PERMANET);
        addressModel.setCountry("Magyarország");
        addressModel.setCity("Budapest");
        addressModel.setPlaceName("Fő utca");
        addressModel.setStreetNumber("1");
        listModels.add(addressModel);

        when(addressDao.listAddresses()).thenReturn(listModels);

        List<Address> retList = addressServiceImpl.listAddresses();

        verify(addressDao, times(1)).listAddresses();
        assertEquals(1, retList.size());
    }

    @Test
    void testCreateAddress() throws MaintainerException {
        com.example.maintainer.model.User userModel = new com.example.maintainer.model.User();
        userModel.setId(1L);
        userModel.setName("Hivekovics Zoltán");

        Address addressModel = new Address();
        addressModel.setUser(userModel);
        addressModel.setAddressType(com.example.maintainer.model.enumeration.AddressType.PERMANET);
        addressModel.setCountry("Magyarország");
        addressModel.setCity("Budapest");
        addressModel.setPlaceName("Fő utca");
        addressModel.setStreetNumber("1");

        when(addressDao.saveOrUpdate(addressModel)).thenReturn(1L);
        when(addressDao.findById(1L)).thenReturn(addressModel);

        Address retAddressModel = addressServiceImpl.createAddress(addressModel);

        verify(addressDao, times(1)).saveOrUpdate(addressModel);
        verify(addressDao, times(1)).findById(1L);
        assertNotNull(retAddressModel);
        assertEquals(addressModel, retAddressModel);
    }

    @Test
    void testModifyAddressById() throws MaintainerException {
        com.example.maintainer.model.User userModel = new com.example.maintainer.model.User();
        userModel.setId(1L);
        userModel.setName("Hivekovics Zoltán");

        Address addressModel = new Address();
        addressModel.setId(1L);
        addressModel.setUser(userModel);
        addressModel.setAddressType(com.example.maintainer.model.enumeration.AddressType.PERMANET);
        addressModel.setCountry("Magyarország");
        addressModel.setCity("Budapest");
        addressModel.setPlaceName("Fő utca");
        addressModel.setStreetNumber("1");

        when(addressDao.existsById(1L)).thenReturn(true);
        when(addressDao.saveOrUpdate(addressModel)).thenReturn(1L);
        when(addressDao.findById(1L)).thenReturn(addressModel);

        Address retAddressModel = addressServiceImpl.modifyAddressById(1L, addressModel);

        verify(addressDao, times(1)).existsById(1L);
        verify(addressDao, times(1)).saveOrUpdate(addressModel);
        verify(addressDao, times(1)).findById(1L);
        assertNotNull(retAddressModel);
        assertEquals(addressModel, retAddressModel);
    }

    @Test
    void testDeleteAddressById() throws MaintainerException {
        when(contactDao.existsByAddressId(1L)).thenReturn(false);
        when(addressDao.existsById(1L)).thenReturn(true);

        addressServiceImpl.deleteAddressById(1L);

        verify(addressDao, times(1)).deleteById(1L);
    }

}
