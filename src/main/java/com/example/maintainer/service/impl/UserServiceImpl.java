package com.example.maintainer.service.impl;

import com.example.maintainer.dao.AddressDao;
import com.example.maintainer.dao.UserDao;
import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.User;
import com.example.maintainer.service.UserService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    AddressDao addressDao;

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User createUser(User user) throws MaintainerException {
        invalidUserId(user);
        validateUserName(user);
        Long entityUserId = userDao.saveOrUpdate(user);
        return userDao.findById(entityUserId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User modifyUserById(Long id, User user) throws MaintainerException {
        validateUserId(id, user);
        existsById(id);
        validateUserName(user);
        Long entityUserId = userDao.saveOrUpdate(user);
        return userDao.findById(entityUserId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserById(Long id) throws MaintainerException {
        validateAddress(id);
        existsById(id);
        userDao.deleteById(id);
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

    private void validateUserName(User user) throws MaintainerException {
        if (StringUtils.isBlank(user.getName())) {
            throw new MaintainerException("Missing username");
        }
    }

    private void validateAddress(Long id) throws MaintainerException {
        if (addressDao.existsByUserId(id)) {
            throw new MaintainerException("Unable to delete - has contact data");
        }
    }

    private void existsById(Long id) throws MaintainerException {
        if (!userDao.existsById(id)) {
            throw new MaintainerException("User not found");
        }
    }

}
