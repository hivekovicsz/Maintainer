package com.example.maintainer.model.extended;

import com.example.maintainer.entity.enumeration.AddressType;
import com.example.maintainer.model.Address;

public class AddressExt extends Address {

    public AddressExt(Long id, AddressType addressType, String country, String city, String placeName, String streetNumber,
                      Long userId, String userName) {
        super.setId(id);
        super.setAddressType(com.example.maintainer.model.enumeration.AddressType.valueOf(addressType.name()));
        super.setCountry(country);
        super.setCity(city);
        super.setPlaceName(placeName);
        super.setStreetNumber(streetNumber);
        super.setUser(new UserExt(userId, userName));
    }
}
