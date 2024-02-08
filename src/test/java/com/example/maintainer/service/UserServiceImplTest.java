package com.example.maintainer.service;

import com.example.maintainer.dao.AddressDao;
import com.example.maintainer.dao.UserDao;
import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.User;
import com.example.maintainer.service.impl.UserServiceImpl;
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
public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    UserDao userDao;

    @Mock
    AddressDao addressDao;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListUsers() {
        List<User> listModels = new ArrayList<>();
        User userModel = new User();
        userModel.setId(1L);
        userModel.setName("Hivekovics Zoltán");
        listModels.add(userModel);

        when(userDao.listUsers()).thenReturn(listModels);

        List<User> retList = userServiceImpl.listUsers();

        verify(userDao, times(1)).listUsers();
        assertEquals(1, retList.size());
    }

    @Test
    void testCreateUser() throws MaintainerException {
        User userModel = new User();
        userModel.setName("Hivekovics Zoltán");

        when(userDao.saveOrUpdate(userModel)).thenReturn(1L);
        when(userDao.findById(1L)).thenReturn(userModel);

        User retUserModel = userServiceImpl.createUser(userModel);

        verify(userDao, times(1)).saveOrUpdate(userModel);
        verify(userDao, times(1)).findById(1L);
        assertNotNull(retUserModel);
        assertEquals(userModel, retUserModel);
    }

    @Test
    void testModifyUserById() throws MaintainerException {
        User userModel = new User();
        userModel.setId(1L);
        userModel.setName("Hivekovics Zoltán");

        when(userDao.existsById(1L)).thenReturn(true);
        when(userDao.saveOrUpdate(userModel)).thenReturn(1L);
        when(userDao.findById(1L)).thenReturn(userModel);

        User retUserModel = userServiceImpl.modifyUserById(1L, userModel);

        verify(userDao, times(1)).existsById(1L);
        verify(userDao, times(1)).saveOrUpdate(userModel);
        verify(userDao, times(1)).findById(1L);
        assertNotNull(retUserModel);
        assertEquals(userModel, retUserModel);
    }

    @Test
    void testDeleteUserById() throws MaintainerException {
        when(addressDao.existsByUserId(1L)).thenReturn(false);
        when(userDao.existsById(1L)).thenReturn(true);

        userServiceImpl.deleteUserById(1L);

        verify(userDao, times(1)).deleteById(1L);
    }

}
