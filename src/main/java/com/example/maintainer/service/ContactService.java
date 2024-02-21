package com.example.maintainer.service;

import java.util.List;

import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.Contact;

public interface ContactService {

	List<Contact> listContacts();

	Contact getContactById(Long id);

	Contact createContact(Contact contact) throws MaintainerException;

	Contact modifyContactById(Long id, Contact contact) throws MaintainerException;

	void deleteContactById(Long id) throws MaintainerException;

}
