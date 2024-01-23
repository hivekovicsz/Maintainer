package com.example.maintainer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.maintainer.entity.User;

public interface UserDao extends JpaRepository<User, Long> {

}
