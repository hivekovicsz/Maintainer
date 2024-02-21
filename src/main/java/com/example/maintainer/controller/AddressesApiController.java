package com.example.maintainer.controller;

import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.Address;
import com.example.maintainer.service.AddressService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressesApiController implements AddressesApi {

    @Autowired
    AddressService addressService;

    @Override
    @GetMapping(value = "/addresses", produces = {"application/json"})
    @ResponseBody
    public List<Address> listAddresses() {
        return addressService.listAddresses();
    }

    @Override
    @GetMapping(value = "/address/{id}", produces = {"application/json"})
    public Address getAddressById(@Parameter(description = "Cím azonosítója") @PathVariable("id") Long id) {
        return addressService.getAddressById(id);
    }

    @Override
    @PostMapping(value = "/address", produces = {"application/json"}, consumes = {"application/json"})
    public Address createAddress(@RequestBody Address address) throws MaintainerException {
        return addressService.createAddress(address);
    }

    @Override
    @PutMapping(value = "/address/{id}", produces = {"application/json"}, consumes = {"application/json"})
    public Address modifyAddressById(@Parameter(description = "Cím azonosítója") @PathVariable("id") Long id, @RequestBody Address address)
            throws MaintainerException {
        return addressService.modifyAddressById(id, address);
    }

    @Override
    @DeleteMapping(value = "/address/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddressById(@Parameter(description = "Cím azonosítója") @PathVariable("id") Long id) throws MaintainerException {
        addressService.deleteAddressById(id);
    }

}
