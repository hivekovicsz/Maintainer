package com.example.maintainer.converter;

import com.example.maintainer.model.Address;

public interface AddressConverter {

	Address convertEntity(com.example.maintainer.entity.Address address);

	com.example.maintainer.entity.Address convertModel(Address address);

}
