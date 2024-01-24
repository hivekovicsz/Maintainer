package com.example.maintainer.exception;

import com.example.maintainer.model.User;
import com.example.maintainer.model.enumeration.AddressType;

import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PUBLIC)

public class MaintainerException extends Exception {
	
	private String code;
	
	public MaintainerException(String code) {
		super() ;		
		this.code = code;		
	}

}
