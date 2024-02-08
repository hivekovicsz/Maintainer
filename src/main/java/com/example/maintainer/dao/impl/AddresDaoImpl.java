package com.example.maintainer.dao.impl;

import com.example.maintainer.dao.AddressDao;
import com.example.maintainer.dao.UserDao;
import com.example.maintainer.entity.User;
import com.example.maintainer.entity.enumeration.AddressType;
import com.example.maintainer.model.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddresDaoImpl implements AddressDao {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public List<Address> listAddresses() {
        TypedQuery<Address> query = em.createQuery(SELECT_ADDRESS.toString(), Address.class);
        return query.getResultList();
    }

    @Override
    public Address findById(Long id) {
        TypedQuery<Address> query = em.createQuery(SELECT_ADDRESS_BY_ID.toString(), Address.class)
                .setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public boolean existsById(Long id) {
        TypedQuery<Long> query = em.createQuery(COUNT_ADDRESS_BY_ID.toString(), Long.class)
                .setParameter("id", id);
        return query.getSingleResult() > 0;
    }

    @Override
    public Long saveOrUpdate(Address address) {
        com.example.maintainer.entity.Address addressEntity;
        if (address.getId() != null) {
            addressEntity = em.find(com.example.maintainer.entity.Address.class, address.getId());
            addressEntity.setUser(em.getReference(User.class, address.getUser().getId()));
            addressEntity.setAddressType(AddressType.valueOf(address.getAddressType().name()));
            addressEntity.setCountry(address.getCountry());
            addressEntity.setCity(address.getCity());
            addressEntity.setPlaceName(address.getPlaceName());
            addressEntity.setStreetNumber(address.getStreetNumber());
            em.merge(addressEntity);
            em.flush();
        } else {
            addressEntity = new com.example.maintainer.entity.Address();
            addressEntity.setUser(em.getReference(User.class, address.getUser().getId()));
            addressEntity.setAddressType(AddressType.valueOf(address.getAddressType().name()));
            addressEntity.setCountry(address.getCountry());
            addressEntity.setCity(address.getCity());
            addressEntity.setPlaceName(address.getPlaceName());
            addressEntity.setStreetNumber(address.getStreetNumber());
            em.persist(addressEntity);
            em.flush();
        }
        return addressEntity.getId();
    }

    @Override
    public void deleteById(Long id) {
        com.example.maintainer.entity.Address addressEntity = em.find(com.example.maintainer.entity.Address.class, id);
        em.remove(addressEntity);
        em.flush();
    }

    @Override
    public boolean existsByUserId(Long userId) {
        TypedQuery<Long> query = em.createQuery(COUNT_ADDRESS_BY_USER_ID.toString(), Long.class)
                .setParameter("userId", userId);
        return query.getSingleResult() > 0;
    }

    @Override
    public boolean existsByUserIdAndAddressTypeAndNotId(Long userId, AddressType addressType, Long id) {
        TypedQuery<Long> query = em.createQuery(COUNT_ADDRESS_BY_USER_ID_AND_ADDRESS_TYPE_AND_NOT_ID.toString(), Long.class)
                .setParameter("userId", userId)
                .setParameter("addressType", addressType)
                .setParameter("id", id);
        return query.getSingleResult() > 0;
    }

    @Override
    public boolean existsByUserIdAndAddressType(Long userId, AddressType addressType) {
        TypedQuery<Long> query = em.createQuery(COUNT_ADDRESS_BY_USER_ID_AND_ADDRESS_TYPE.toString(), Long.class)
                .setParameter("userId", userId)
                .setParameter("addressType", addressType);
        return query.getSingleResult() > 0;
    }
}
