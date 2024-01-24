package com.example.maintainer.model;

import java.util.Objects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)

public class Contact {

	private Long id;

	private Address address;

	private String email;

	private String phoneNumber;


	@Override
	public int hashCode() {
		return Objects.hash(address, email, id, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(phoneNumber, other.phoneNumber);
	}
	
	@Override
	public String toString() {
		return "Contact [id=" + id + ", address=" + address + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}

}
