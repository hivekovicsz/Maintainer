package com.example.maintainer.controller;

import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.Contact;
import com.example.maintainer.service.ContactService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactsApiController implements ContactsApi {

    @Autowired
    ContactService contactService;

    @Override
    @GetMapping(value = "/contacts", produces = {"application/json"})
    @ResponseBody
    public List<Contact> listContacts() {
        return contactService.listContacts();
    }

    @Override
    @GetMapping(value = "/contact/{id}", produces = {"application/json"})
    public Contact getContactById(@Parameter(description = "Szerződés azonosítója") @PathVariable("id") Long id) {
        return contactService.getContactById(id);
    }

    @Override
    @PostMapping(value = "/contact", produces = {"application/json"}, consumes = {"application/json"})
    public Contact createContact(@RequestBody Contact contact) throws MaintainerException {
        return contactService.createContact(contact);
    }

    @Override
    @PutMapping(value = "/contact/{id}", produces = {"application/json"}, consumes = {"application/json"})
    public Contact modifyContactById(@Parameter(description = "Szerződés azonosítója") @PathVariable("id") Long id, @RequestBody Contact contact) throws MaintainerException {
        return contactService.modifyContactById(id, contact);
    }

    @Override
    @DeleteMapping(value = "/contact/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContactById(@Parameter(description = "Szerződés azonosítója") @PathVariable("id") Long id) throws MaintainerException {
        contactService.deleteContactById(id);
    }


}
