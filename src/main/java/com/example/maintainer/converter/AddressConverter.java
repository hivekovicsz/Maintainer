package com.example.maintainer.converter;

import com.example.maintainer.entity.Address;

public interface AddressConverter {

	com.example.maintainer.model.Address convertEntity(Address address);

	Address convertModel(com.example.maintainer.model.Address address);

}
