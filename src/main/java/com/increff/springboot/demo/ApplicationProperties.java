package com.increff.springboot.demo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ApplicationProperties {
    @Value("${rest.template.keep.alive.timeout:30}")
    private Integer restTemplateKeepAlive;

    @Value("${auth.baseUrl}")
    private String baseUrl;

    @Value("${auth.appToken}")
    private String authAppToken;

    @Value("${account.readTimeOut:30}")
    private Integer accountReadTimeOut;
}
