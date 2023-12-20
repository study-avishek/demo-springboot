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
@RequestMapping(value = "/admin/api")
public class AdminController {

    @Autowired
    private UserApi api;

    @Operation(summary = "Add user", description = "add an user with his password to database")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UserData addUser(@RequestBody UserForm form) throws IllegalAccessException, ApiException {
        return api.addUser(form);
    }
}
