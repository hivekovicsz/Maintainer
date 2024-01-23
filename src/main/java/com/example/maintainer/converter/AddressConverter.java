package com.example.maintainer.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.maintainer.entity.Address;
import com.example.maintainer.model.enumeration.AddressType;

@Component
public class AddressConverter {

	@Autowired
	UserConverter userConverter;

	public com.example.maintainer.model.Address convertEntity(Address address) {
		com.example.maintainer.model.Address retAddress = new com.example.maintainer.model.Address();
		retAddress.setId(address.getId());
		retAddress.setUser(userConverter.convertEntity(address.getUser()));
		retAddress.setAddressType(AddressType.valueOf(address.getAddressType().name()));
		retAddress.setCountry(address.getCountry());
		retAddress.setCity(address.getCity());
		retAddress.setPlaceName(address.getPlaceName());
		retAddress.setStreetNumber(address.getStreetNumber());

		return retAddress;
	}

	public Address convertModel(com.example.maintainer.model.Address address) {
		Address retAddress = new Address();
		retAddress.setId(address.getId());
		retAddress.setUser(userConverter.convertModel(address.getUser()));
		retAddress.setAddressType(com.example.maintainer.entity.enumeration.AddressType.valueOf(address.getAddressType().name()));
		retAddress.setCountry(address.getCountry());
		retAddress.setCity(address.getCity());
		retAddress.setPlaceName(address.getPlaceName());
		retAddress.setStreetNumber(address.getStreetNumber());

		return retAddress;
	}

}
