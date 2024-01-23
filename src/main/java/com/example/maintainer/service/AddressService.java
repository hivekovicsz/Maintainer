package com.example.maintainer.service;

import java.util.List;

import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.Address;

public interface AddressService {

	List<Address> listAddresses();

	Address createAddress(Address address) throws MaintainerException;

	Address modifyAddressById(Long id, Address address) throws MaintainerException;

	void deleteAddressById(Long id);

}
