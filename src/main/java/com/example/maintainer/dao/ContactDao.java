package com.example.maintainer.dao;

import com.example.maintainer.model.Contact;
import com.example.maintainer.model.extended.ContactExt;

import java.util.List;

public interface ContactDao {

	StringBuilder SELECT_CONTACT = new StringBuilder()
			.append("SELECT new ").append(ContactExt.class.getCanonicalName()).append("( \n")
			.append("contact.id, contact.email, contact.phoneNumber, \n")
			.append("address.id, address.addressType, address.country, address.city, \n")
			.append("address.placeName, address.streetNumber, \n")
			.append("user.id, user.name \n")
			.append(") \n")
			.append("FROM Contact contact \n")
			.append("INNER JOIN Address address ON address = contact.address \n")
			.append("INNER JOIN User user ON user = address.user \n")
			.append("WHERE 1=1 \n");
	StringBuilder SELECT_CONTACT_BY_ID = new StringBuilder()
			.append(SELECT_CONTACT)
			.append("AND contact.id = :id \n");
	StringBuilder COUNT_CONTACT_BY_ID = new StringBuilder()
			.append("SELECT COUNT(1) \n")
			.append("FROM Contact contact \n")
			.append("WHERE contact.id = :id \n");
	StringBuilder COUNT_CONTACT_BY_ADDRESS_ID = new StringBuilder()
			.append("SELECT COUNT(1) \n")
			.append("FROM Contact contact \n")
			.append("INNER JOIN Address address ON address = contact.address \n")
			.append("WHERE address.id = :addressId \n");

	List<Contact> listContacts();

	Contact findById(Long entityContactId);

	boolean existsById(Long id);

	Long saveOrUpdate(Contact contact);

	void deleteById(Long id);

	boolean existsByAddressId(Long addressId);

}
