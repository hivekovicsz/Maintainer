package com.example.maintainer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.maintainer.entity.User;

public interface UserDao extends JpaRepository<User, Long> {

	static final String SELECT_USER = "SELECT new com.example.maintainer.model.extended.UserExt( \n"
			+ "u.id, u.name \n" + ") \n"
			+ "FROM User u \n" 
			+ "WHERE 1=1 \n";

	@Query(value = SELECT_USER)
	List<com.example.maintainer.model.User> findAllUsers();

}
