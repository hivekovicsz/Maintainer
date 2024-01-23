package com.example.maintainer.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)

public class Address {

	private Long id;

	private User user;

	private String addressType;

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
