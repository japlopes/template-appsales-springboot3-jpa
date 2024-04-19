package com.modelmono.appwholesale.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.modelmono.appwholesale.entities.Category;
import com.modelmono.appwholesale.entities.Order;
import com.modelmono.appwholesale.entities.User;
import com.modelmono.appwholesale.entities.enums.OrderStatus;
import com.modelmono.appwholesale.repositories.CategoryRepository;
import com.modelmono.appwholesale.repositories.OrderRepository;
import com.modelmono.appwholesale.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Foods");
		Category cat2 = new Category(null, "Drinks");
		Category cat3 = new Category(null, "Hygiene and Beauty");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		User u1 = new User(null, "Maria Brown", "mariaB@gmail.com", "11991234500", "@der3456");
		User u2 = new User(null, "Johnny Bravo", "johnnyB@gmail.com", "13994251942", "123456q");
		
		Order o1 = new Order(null, Instant.parse("2024-04-14T23:15:07Z"), OrderStatus.PAID, u2);
		Order o2 = new Order(null, Instant.parse("2024-04-14T23:17:23Z"), OrderStatus.WAITING_PAYMENT, u1);
		Order o3 = new Order(null, Instant.parse("2024-04-14T23:21:55Z"), OrderStatus.WAITING_PAYMENT ,u2);
		
		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
	}

}
