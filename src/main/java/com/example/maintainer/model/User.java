package com.example.maintainer.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)

public class User {

	private Long id;
	private String name;

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

}
