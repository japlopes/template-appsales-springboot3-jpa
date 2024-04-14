package com.modelmono.appwholesale.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.modelmono.appwholesale.entities.User;
import com.modelmono.appwholesale.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Maria Brown", "mariaB@gmail.com", "11991234500", "@der3456");
		User u2 = new User(null, "Johnny Bravo", "johnnyB@gmail.com", "13994251942", "123456q");
		
		userRepository.saveAll(Arrays.asList(u1,u2));
		
	}

}
