package com.example.maintainer.controller;

import java.util.List;

import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.Contact;

public interface ContactsApi {

	List<Contact> listContacts();

	Contact createContact(Contact contact) throws MaintainerException;

	Contact modifyContactById(Long id, Contact contact) throws MaintainerException;

	void deleteContactById(Long id) throws MaintainerException;

}
