package com.example.maintainer.service.impl;

import com.example.maintainer.dao.ContactDao;
import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.Contact;
import com.example.maintainer.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactDao contactDao;

    @Override
    public List<Contact> listContacts() {
        return contactDao.listContacts();
    }

    @Override
    public Contact getContactById(Long id) {
        return contactDao.findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Contact createContact(Contact contact) throws MaintainerException {
        invalidContactId(contact);
        Long entityContactId = contactDao.saveOrUpdate(contact);
        return contactDao.findById(entityContactId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Contact modifyContactById(Long id, Contact contact) throws MaintainerException {
        validateContactId(id, contact);
        existisById(id);
        Long entityContactId = contactDao.saveOrUpdate(contact);
        return contactDao.findById(entityContactId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteContactById(Long id) throws MaintainerException {
        existisById(id);
        contactDao.deleteById(id);
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

    private void existisById(Long id) throws MaintainerException {
        if (!contactDao.existsById(id)) {
            throw new MaintainerException("Contact not found");
        }
    }

}
