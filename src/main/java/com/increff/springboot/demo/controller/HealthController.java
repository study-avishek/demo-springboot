package com.increff.springboot.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/public/api")
public class HealthController {
    @Operation(summary = "Health api", description = "Wow we can also write detailed description")
    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public String smoke(){
        return "Chal gaya vai";
    }
}
