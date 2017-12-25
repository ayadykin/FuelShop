package com.ayadykin.test.fuel.shop.rest;

import static com.ayadykin.test.fuel.shop.rest.Urls.USER_URL;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = USER_URL)
public class UserController {

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public void getOrders() {
	}

	@PostMapping
	public void createtOrder() {
	}

	@PutMapping
	public void upateOrder() {
	}

	@DeleteMapping
	public void deleteOrders() {
	}
}
