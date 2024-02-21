package com.example.maintainer.controller;

import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.User;
import com.example.maintainer.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersApiController implements UsersApi {

    @Autowired
    UserService userService;

    @Override
    @GetMapping(value = "/users", produces = {"application/json"})
    @ResponseBody
    public List<User> listUsers() {
        return userService.listUsers();
    }

    @Override
    @GetMapping(value = "/user/{id}", produces = {"application/json"})
    @ResponseBody
    public User getUserById(@Parameter(description = "Felhasználó azonosítója") @PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @Override
    @PostMapping(value = "/user", produces = {"application/json"}, consumes = {"application/json"})
    public User createUser(@RequestBody User user) throws MaintainerException {
        return userService.createUser(user);
    }

    @Override
    @PutMapping(value = "/user/{id}", produces = {"application/json"}, consumes = {"application/json"})
    public User modifyUserById(@Parameter(description = "Felhasználó azonosítója") @PathVariable("id") Long id, @RequestBody User user) throws MaintainerException {
        return userService.modifyUserById(id, user);
    }

    @Override
    @DeleteMapping(value = "/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@Parameter(description = "Felhasználó azonosítója") @PathVariable("id") Long id) throws MaintainerException {
        userService.deleteUserById(id);
    }

}
