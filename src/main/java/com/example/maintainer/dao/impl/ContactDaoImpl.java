package com.example.maintainer.dao.impl;

import com.example.maintainer.dao.ContactDao;
import com.example.maintainer.dao.UserDao;
import com.example.maintainer.entity.Address;
import com.example.maintainer.model.Contact;
import com.example.maintainer.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.maintainer.dao.UserDao.COUNT_USER_BY_ID;

@Repository
public class ContactDaoImpl implements ContactDao {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public List<Contact> listContacts() {
        TypedQuery<Contact> query = em.createQuery(SELECT_CONTACT.toString(), Contact.class);
        return query.getResultList();
    }

    @Override
    public Contact findById(Long id) {
        TypedQuery<Contact> query = em.createQuery(SELECT_CONTACT_BY_ID.toString(), Contact.class)
                .setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public boolean existsById(Long id) {
        TypedQuery<Long> query = em.createQuery(COUNT_CONTACT_BY_ID.toString(), Long.class)
                .setParameter("id", id);
        return query.getSingleResult() > 0;
    }

    @Override
    public Long saveOrUpdate(Contact contact) {
        com.example.maintainer.entity.Contact contactEntity;
        if (contact.getId() != null) {
            contactEntity = em.find(com.example.maintainer.entity.Contact.class, contact.getId());
            contactEntity.setAddress(em.getReference(Address.class, contact.getAddress().getId()));
            contactEntity.setEmail(contact.getEmail());
            contactEntity.setPhoneNumber(contact.getPhoneNumber());
            em.merge(contactEntity);
            em.flush();
        } else {
            contactEntity = new com.example.maintainer.entity.Contact();
            contactEntity.setAddress(em.getReference(Address.class, contact.getAddress().getId()));
            contactEntity.setEmail(contact.getEmail());
            contactEntity.setPhoneNumber(contact.getPhoneNumber());
            em.persist(contactEntity);
            em.flush();
        }
        return contactEntity.getId();
    }

    @Override
    public void deleteById(Long id) {
        com.example.maintainer.entity.Contact contactEntity = em.find(com.example.maintainer.entity.Contact.class, id);
        em.remove(contactEntity);
        em.flush();
    }

    @Override
    public boolean existsByAddressId(Long addressId) {
        TypedQuery<Long> query = em.createQuery(COUNT_CONTACT_BY_ADDRESS_ID.toString(), Long.class)
                .setParameter("addressId", addressId);
        return query.getSingleResult() > 0;
    }
}
