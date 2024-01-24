package com.example.maintainer.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)

public class ErrorMessage {
	
	private String code;

	@Override
	public String toString() {
		return "ErrorMessage [code=" + code + "]";
	}
	

}
