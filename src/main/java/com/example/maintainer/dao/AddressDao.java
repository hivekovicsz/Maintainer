package com.example.maintainer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.maintainer.entity.Address;

public interface AddressDao extends JpaRepository<Address, Long> {

}
