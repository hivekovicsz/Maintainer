package com.example.maintainer.model;

import java.util.Objects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)

public class ErrorMessage {
	
	private String code;

	@Override
	public int hashCode() {
		return Objects.hash(code);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorMessage other = (ErrorMessage) obj;
		return Objects.equals(code, other.code);
	}
	
	@Override
	public String toString() {
		return "ErrorMessage [code=" + code + "]";
	}


}
