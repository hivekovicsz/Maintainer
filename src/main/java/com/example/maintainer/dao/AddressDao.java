package com.example.maintainer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.maintainer.entity.Address;
import com.example.maintainer.entity.enumeration.AddressType;

public interface AddressDao extends JpaRepository<Address, Long> {

	int countByUserId(Long userId);
	
	int countByUserIdAndAddressType(Long userId, AddressType addressType);
	
	@Query("select count(1) from Address a where a.user.id = :userId and a.addressType = :addressType and a.id != :id")
	int countByUserIdAndAddressTypeAndNotId(@Param("userId") Long userId, @Param("addressType") AddressType addressType, @Param("id") Long id);
}
