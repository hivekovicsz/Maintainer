package com.example.maintainer.model;

import java.util.Objects;

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
	public int hashCode() {
		return Objects.hash(addressType, city, country, id, placeName, streetNumber, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return addressType == other.addressType && Objects.equals(city, other.city)
				&& Objects.equals(country, other.country) && Objects.equals(id, other.id)
				&& Objects.equals(placeName, other.placeName) && Objects.equals(streetNumber, other.streetNumber)
				&& Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", addressType=" + addressType + ", country=" + country + ", city=" + city
				+ ", placeName=" + placeName + ", streetNumber=" + streetNumber + "]";
	}
}
