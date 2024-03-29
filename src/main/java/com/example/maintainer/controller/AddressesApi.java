package com.example.maintainer.controller;

import java.util.List;

import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.Address;

public interface AddressesApi {

	List<Address> listAddresses();

	Address getAddressById(Long id);
	
	Address createAddress(Address address) throws MaintainerException;

	Address modifyAddressById(Long id, Address address) throws MaintainerException;

	void deleteAddressById(Long id) throws MaintainerException;

}
