package com.example.maintainer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.maintainer.entity.Contact;

public interface ContactDao extends JpaRepository<Contact, Long> {

}
