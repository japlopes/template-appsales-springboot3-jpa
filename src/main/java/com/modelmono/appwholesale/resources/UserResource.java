package com.modelmono.appwholesale.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modelmono.appwholesale.entities.User;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@GetMapping
	public ResponseEntity<User> findAll(){
		User u = new User(1L, "Johnny", "johnnyB@gmail.com", "13991009779", "123@345");
		return ResponseEntity.ok().body(u);
	}

}
