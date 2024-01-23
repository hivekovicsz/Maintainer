package com.example.maintainer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.Address;
import com.example.maintainer.service.AddressService;

@RestController
public class AddressesApiController implements AddressesApi {

	@Autowired
	AddressService addressService;

	@Override
	@GetMapping(value = "/addresses", produces = { "application/json" })
	@ResponseBody
	public List<Address> listAddresses() {
		return addressService.listAddresses();
	}

	@Override
	@PostMapping(value = "/address", produces = { "application/json" }, consumes = { "application/json" })
	public Address createAddress(@RequestBody Address address) throws MaintainerException {
		return addressService.createAddress(address);
	}

	@Override
	@PutMapping(value = "/address/{id}", produces = { "application/json" }, consumes = { "application/json" })
	public Address modifyAddressById(@PathVariable("id") Long id, @RequestBody Address address)
			throws MaintainerException {
		return addressService.modifyAddressById(id, address);
	}

	@Override
	@DeleteMapping(value = "/address/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAddressById(@PathVariable("id") Long id) throws MaintainerException {		
		addressService.deleteAddressById(id);
	}

}
