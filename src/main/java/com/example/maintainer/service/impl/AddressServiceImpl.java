package com.example.maintainer.service.impl;

import com.example.maintainer.dao.AddressDao;
import com.example.maintainer.dao.ContactDao;
import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.Address;
import com.example.maintainer.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressDao addressDao;

    @Autowired
    ContactDao contactDao;

    @Override
    public List<Address> listAddresses() {
        return addressDao.listAddresses();
    }

    @Override
    public Address getAddressById(Long id) {
        return addressDao.findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Address createAddress(Address address) throws MaintainerException {
        invalidUserId(address);
        validateAddressType(address);
        Long entityAddressId = addressDao.saveOrUpdate(address);
        return addressDao.findById(entityAddressId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Address modifyAddressById(Long id, Address address) throws MaintainerException {
        validateAddressId(id, address);
        existsById(id);
        validateAddressType(address);
        Long entityAddressId = addressDao.saveOrUpdate(address);
        return addressDao.findById(entityAddressId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAddressById(Long id) throws MaintainerException {
        existsById(id);
        validateContact(id);
        addressDao.deleteById(id);
    }

    private void invalidUserId(Address address) throws MaintainerException {
        if (address.getUser() == null || address.getUser().getId() == null) {
            throw new MaintainerException("Invalid user model");
        }
    }

    private void validateAddressId(Long id, Address address) throws MaintainerException {
        if (!id.equals(address.getId())) {
            throw new MaintainerException("Invalid address model");
        }
    }

    private void validateAddressType(Address address) throws MaintainerException {
        if (address.getAddressType() == null) {
            throw new MaintainerException("Missing address type");
        }
        if (address.getId() != null) {
            if (addressDao.existsByUserIdAndAddressTypeAndNotId(address.getUser().getId(),
                    com.example.maintainer.entity.enumeration.AddressType.valueOf(address.getAddressType().name()),
                    address.getId())) {
                throw new MaintainerException("Invalid address type");
            }
        } else {
            if (addressDao.existsByUserIdAndAddressType(address.getUser().getId(),
                    com.example.maintainer.entity.enumeration.AddressType
                            .valueOf(address.getAddressType().name()))) {
                throw new MaintainerException("Invalid address type");
            }
        }
    }

    private void validateContact(Long id) throws MaintainerException {
        if (contactDao.existsByAddressId(id)) {
            throw new MaintainerException("Unable to delete - has contact data");
        }
    }

    private void existsById(Long id) throws MaintainerException {
        if (!addressDao.existsById(id)) {
            throw new MaintainerException("Address not found");
        }
    }

}
