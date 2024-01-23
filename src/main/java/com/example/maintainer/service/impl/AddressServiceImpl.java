package com.example.maintainer.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.maintainer.converter.AddressConverter;
import com.example.maintainer.dao.AddressDao;
import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.Address;
import com.example.maintainer.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressDao addressDao;

	@Autowired
	AddressConverter addressConverter;

	@Override
	public List<Address> listAddresses() {
		return addressDao.findAll().stream().map(address -> addressConverter.convertEntity(address))
				.collect(Collectors.toList());
	}

	@Override
	public Address createAddress(Address address) throws MaintainerException {
		invalidUserId(address);
		com.example.maintainer.entity.Address entityAddress = addressDao
				.saveAndFlush(addressConverter.convertModel(address));
		return addressConverter.convertEntity(entityAddress);
	}

	@Override
	public Address modifyAddressById(Long id, Address address) throws MaintainerException {
		validateAddressId(id, address);		
		if (addressDao.existsById(id)) {
			com.example.maintainer.entity.Address entityAddress = addressDao
					.saveAndFlush(addressConverter.convertModel(address));
			return addressConverter.convertEntity(entityAddress);
		}
		return null;
		
	}

	@Override
	public void deleteAddressById(Long id) {
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

}
