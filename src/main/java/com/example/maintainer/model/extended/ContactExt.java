package com.example.maintainer.model.extended;

import com.example.maintainer.entity.enumeration.AddressType;
import com.example.maintainer.model.Address;
import com.example.maintainer.model.Contact;

public class ContactExt extends Contact {

    public ContactExt(Long id, String email, String phoneNumber,
                      Long addressId, AddressType addressType, String country, String city, String placeName, String streetNumber,
                      Long userId, String userName) {
        super.setId(id);
        super.setEmail(email);
        super.setPhoneNumber(phoneNumber);
        super.setAddress(new AddressExt(addressId, addressType, country, city, placeName, streetNumber, userId, userName));
    }
}
