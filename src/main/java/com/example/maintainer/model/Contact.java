package com.example.maintainer.model;

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
	public String toString() {
		return "Contact [id=" + id + ", address=" + address + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}

}
