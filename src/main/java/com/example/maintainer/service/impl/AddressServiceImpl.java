package com.example.maintainer.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.maintainer.converter.AddressConverter;
import com.example.maintainer.dao.AddressDao;
import com.example.maintainer.dao.ContactDao;
import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.Address;
import com.example.maintainer.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressDao addressDao;

	@Autowired
	ContactDao contactDao;

	@Autowired
	AddressConverter addressConverter;

	@Override
	public List<Address> listAddresses() {
		return addressDao.findAll().stream().map(address -> addressConverter.convertEntity(address))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Address createAddress(Address address) throws MaintainerException {
		invalidUserId(address);
		validateAddressType(address);
		com.example.maintainer.entity.Address entityAddress = addressDao
				.saveAndFlush(addressConverter.convertModel(address));
		return addressConverter.convertEntity(entityAddress);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Address modifyAddressById(Long id, Address address) throws MaintainerException {
		validateAddressId(id, address);		
		if (addressDao.existsById(id)) {
			validateAddressType(address);
			com.example.maintainer.entity.Address entityAddress = addressDao
					.saveAndFlush(addressConverter.convertModel(address));
			return addressConverter.convertEntity(entityAddress);
		}
		throw new MaintainerException("Address not found");

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteAddressById(Long id) throws MaintainerException {
		validateContact(id);
		addressDao.deleteById(id);
		addressDao.flush();
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
			if (addressDao.countByUserIdAndAddressTypeAndNotId(address.getUser().getId(),
					com.example.maintainer.entity.enumeration.AddressType.valueOf(address.getAddressType().name()),
					address.getId()) > 0) {
				throw new MaintainerException("Invalid address type");
			}
		} else {
			if (addressDao.countByUserIdAndAddressType(address.getUser().getId(),
					com.example.maintainer.entity.enumeration.AddressType
							.valueOf(address.getAddressType().name())) > 0) {
				throw new MaintainerException("Invalid address type");
			}
		}
	}

	private void validateContact(Long id) throws MaintainerException {
		if (contactDao.countByAddressId(id) > 0) {
			throw new MaintainerException("Unable to delete - has contact data");
		}
	}

}
