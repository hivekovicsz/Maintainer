package com.example.maintainer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.Contact;
import com.example.maintainer.service.ContactService;

@RestController
public class ContactsApiController implements ContactsApi {

	@Autowired
	ContactService contactService; 

	@Override
	@GetMapping(value = "/contacts", produces = { "application/json" })
	@ResponseBody
	public List<Contact> listContacts() {
		return contactService.listContacts();
	}

	@Override
	@PostMapping(value = "/contact", produces = { "application/json" }, consumes = { "application/json" })
	public Contact createContact(@RequestBody Contact contact) throws MaintainerException {
		return contactService.createContact(contact);
	}

	@Override
	@PutMapping(value = "/contact/{id}", produces = { "application/json" }, consumes = { "application/json" })
	public Contact modifyContactById(@PathVariable("id") Long id, @RequestBody Contact contact) throws MaintainerException {
		return contactService.modifyContactById(id, contact);
	}

	@Override
	@DeleteMapping(value = "/contact/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteContactById(@PathVariable("id") Long id) {
		contactService.deleteContactById(id);
	}

}
