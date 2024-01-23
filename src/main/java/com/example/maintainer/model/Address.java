package com.example.maintainer.model;

import com.example.maintainer.model.enumeration.AddressType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)

public class Address {

	private Long id;

	private User user;

	private AddressType addressType;

	private String country;

	private String city;

	private String placeName;

	private String streetNumber;

	@Override
	public String toString() {
		return "Address [id=" + id + ", addressType=" + addressType + ", country=" + country + ", city=" + city
				+ ", placeName=" + placeName + ", streetNumber=" + streetNumber + "]";
	}

}
