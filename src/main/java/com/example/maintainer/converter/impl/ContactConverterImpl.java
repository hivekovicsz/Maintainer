package com.example.maintainer.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.maintainer.converter.ContactConverter;
import com.example.maintainer.entity.Contact;

@Component
public class ContactConverterImpl implements ContactConverter {

	@Autowired
	AddressConverterImpl addressConverter;

	@Override
	public com.example.maintainer.model.Contact convertEntity(Contact contact) {
		com.example.maintainer.model.Contact retContact = new com.example.maintainer.model.Contact();
		retContact.setId(contact.getId());
		retContact.setAddress(addressConverter.convertEntity(contact.getAddress()));
		retContact.setEmail(contact.getEmail());
		retContact.setPhoneNumber(contact.getPhoneNumber());

		return retContact;
	}

	@Override
	public Contact convertModel(com.example.maintainer.model.Contact contact) {
		Contact retContact = new Contact();
		retContact.setId(contact.getId());
		retContact.setAddress(addressConverter.convertModel(contact.getAddress()));
		retContact.setEmail(contact.getEmail());
		retContact.setPhoneNumber(contact.getPhoneNumber());

		return retContact;
	}
}
