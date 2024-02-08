package com.example.maintainer.dao;

import com.example.maintainer.model.User;
import com.example.maintainer.model.extended.UserExt;

import java.util.List;

public interface UserDao {
    StringBuilder SELECT_USER = new StringBuilder()
            .append("SELECT new ").append(UserExt.class.getCanonicalName()).append("( \n")
            .append("user.id, user.name \n")
            .append(") \n")
            .append("FROM User user \n")
            .append("WHERE 1=1 \n");
    StringBuilder SELECT_USER_BY_ID = new StringBuilder()
            .append(SELECT_USER)
            .append("AND user.id = :id \n");
    StringBuilder COUNT_USER_BY_ID = new StringBuilder()
            .append("SELECT COUNT(1) \n")
            .append("FROM User user \n")
            .append("WHERE user.id = :id \n");

    List<User> listUsers();

    User findById(Long id);

    boolean existsById(Long id);

    Long saveOrUpdate(User user);

    void deleteById(Long id);

}
