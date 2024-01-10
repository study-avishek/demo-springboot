package com.increff.springboot.demo;

import com.increff.account.client.AuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;


@SpringBootApplication
@ComponentScan(value = {"com.increff.commons.springboot.audit", "com.increff.account.client"} )
public class DemoApplication {
	@Autowired
	private ApplicationProperties properties;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public AuthClient authClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		return new AuthClient(properties.getBaseUrl(), properties.getAuthAppToken());
	}
}
