package com.example.maintainer.converter;

import com.example.maintainer.entity.Contact;

public interface ContactConverter {

	com.example.maintainer.model.Contact convertEntity(Contact contact);

	Contact convertModel(com.example.maintainer.model.Contact contact);

}
