package com.increff.springboot.demo.controller;

import com.increff.commons.springboot.common.ApiException;
import com.increff.springboot.demo.api.UserApi;
import com.increff.springboot.demo.model.UserData;
import com.increff.springboot.demo.model.UserForm;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/app/api")
public class AppController {
    @Autowired
    private UserApi api;

    @Operation(summary = "Add user", description = "add an user with his password to database")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UserData addUser(@RequestBody UserForm form) throws IllegalAccessException, ApiException {
        return api.addUser(form);
    }

    @Operation(summary = "Health api", description = "Wow we can also write detailed description")
    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public String smoke(){
        return "Admin nehi hoon vai";
    }
}
