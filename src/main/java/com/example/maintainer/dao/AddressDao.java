package com.example.maintainer.dao;

import com.example.maintainer.model.Address;
import com.example.maintainer.model.extended.AddressExt;

import com.example.maintainer.entity.enumeration.AddressType;

import java.util.List;

public interface AddressDao  {

	StringBuilder SELECT_ADDRESS = new StringBuilder()
			.append("SELECT new ").append(AddressExt.class.getCanonicalName()).append("( \n")
			.append("address.id, address.addressType, address.country, address.city, \n")
			.append("address.placeName, address.streetNumber, \n")
			.append("user.id, user.name \n")
			.append(") \n")
			.append("FROM Address address \n")
			.append("INNER JOIN User user ON user = address.user \n")
			.append("WHERE 1=1 \n");

	StringBuilder SELECT_ADDRESS_BY_ID = new StringBuilder()
			.append(SELECT_ADDRESS)
			.append("AND address.id = :id \n");

	StringBuilder COUNT_ADDRESS_BY_ID = new StringBuilder()
			.append("SELECT COUNT(1) \n")
			.append("FROM Address address \n")
			.append("WHERE address.id = :id \n");

	StringBuilder COUNT_ADDRESS_BY_USER_ID = new StringBuilder()
			.append("SELECT COUNT(1) \n")
			.append("FROM Address address \n")
			.append("INNER JOIN User user ON user = address.user \n")
			.append("WHERE user.id = :userId \n");

	StringBuilder COUNT_ADDRESS_BY_USER_ID_AND_ADDRESS_TYPE = new StringBuilder()
			.append("SELECT COUNT(1) \n")
			.append("FROM Address address \n")
			.append("INNER JOIN User user ON user = address.user \n")
			.append("WHERE user.id = :userId \n")
			.append("AND address.addressType = :addressType \n");
	StringBuilder COUNT_ADDRESS_BY_USER_ID_AND_ADDRESS_TYPE_AND_NOT_ID = new StringBuilder()
			.append(COUNT_ADDRESS_BY_USER_ID_AND_ADDRESS_TYPE)
			.append("AND address.id <> :id");

	List<Address> listAddresses();

	Address findById(Long id);

	boolean existsById(Long id);

	Long saveOrUpdate(Address address);

	void deleteById(Long id);

	boolean existsByUserId(Long userId);

	boolean existsByUserIdAndAddressTypeAndNotId(Long userId, AddressType addressType, Long id);

	boolean existsByUserIdAndAddressType(Long userId, AddressType addressType);

}
