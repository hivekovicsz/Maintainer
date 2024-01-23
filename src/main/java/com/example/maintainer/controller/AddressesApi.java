package com.example.maintainer.controller;

import java.util.List;

import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.Address;

public interface AddressesApi {

	public List<Address> listAddresses();
	
	Address createAddress(Address address) throws MaintainerException;

}
