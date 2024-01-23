package com.example.maintainer.model.extended;

import com.example.maintainer.model.User;

public class UserExt extends User {

	public UserExt(Long id, String name) {
		super.setId(id);
		super.setName(name);
	}

}
