package com.increff.springboot.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserData extends UserForm{
    private Integer id;
    private Boolean enabled;
}
