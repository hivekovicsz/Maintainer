package com.example.maintainer.exception;

public class MaintainerException extends Exception {
	
	private String code;
	
	public MaintainerException(String code) {
		super() ;		
		this.code = code;		
	}

}
