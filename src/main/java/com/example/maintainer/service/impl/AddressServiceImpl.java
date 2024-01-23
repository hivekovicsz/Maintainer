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
		validateUserId(address);
		com.example.maintainer.entity.Address entityAddress = addressDao.saveAndFlush(addressConverter.convertModel(address));
		return addressConverter.convertEntity(entityAddress);
	}

	private void validateUserId(Address address) throws MaintainerException {
		if (address.getUser() == null || address.getUser().getId() == null) {
			throw new MaintainerException("Invalid user model");
		}
		
	}

}
