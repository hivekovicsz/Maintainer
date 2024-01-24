package com.example.maintainer.converter;

import com.example.maintainer.model.Contact;

public interface ContactConverter {

	Contact convertEntity(com.example.maintainer.entity.Contact contact);

	com.example.maintainer.entity.Contact convertModel(Contact contact);

}
