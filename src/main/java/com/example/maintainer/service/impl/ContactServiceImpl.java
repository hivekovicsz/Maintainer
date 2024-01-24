package com.example.maintainer.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.maintainer.converter.ContactConverter;
import com.example.maintainer.dao.ContactDao;
import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.Contact;
import com.example.maintainer.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	ContactDao contactDao;

	@Autowired
	ContactConverter contactConverter;

	@Override
	public List<Contact> listContacts() {
		return contactDao.findAll().stream().map(contact -> contactConverter.convertEntity(contact))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Contact createContact(Contact contact) throws MaintainerException {
		invalidContactId(contact);
		com.example.maintainer.entity.Contact entityContact = contactDao
				.saveAndFlush(contactConverter.convertModel(contact));
		return contactConverter.convertEntity(entityContact);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Contact modifyContactById(Long id, Contact contact) throws MaintainerException {
		validateContactId(id, contact);
		if (contactDao.existsById(id)) {
			com.example.maintainer.entity.Contact entityContact = contactDao
					.saveAndFlush(contactConverter.convertModel(contact));
			return contactConverter.convertEntity(entityContact);
		}
		throw new MaintainerException("Contact not found");
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteContactById(Long id) {
		contactDao.deleteById(id);
		contactDao.flush();
	}

	private void invalidContactId(Contact contact) throws MaintainerException {
		if (contact.getId() != null) {
			throw new MaintainerException("Invalid contact model");
		}
	}

	private void validateContactId(Long id, Contact contact) throws MaintainerException {
		if (!id.equals(contact.getId())) {
			throw new MaintainerException("Invalid contact model");
		}
	}

}
