package com.modelmono.appwholesale.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.modelmono.appwholesale.entities.Category;
import com.modelmono.appwholesale.entities.Order;
import com.modelmono.appwholesale.entities.Product;
import com.modelmono.appwholesale.entities.User;
import com.modelmono.appwholesale.entities.enums.OrderStatus;
import com.modelmono.appwholesale.repositories.CategoryRepository;
import com.modelmono.appwholesale.repositories.OrderRepository;
import com.modelmono.appwholesale.repositories.ProductRepository;
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
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Foods");
		Category cat2 = new Category(null, "Drinks");
		Category cat3 = new Category(null, "Hygiene and Beauty");
		
		Product p1 = new Product(null,"Kero Coconut","Fruit or vegetable, juice mix", 3.79, "");
		Product p2 = new Product(null, "Toddy prisma 200", "Ready-made lacteal chocolate drink", 4.89, "");
		Product p3 = new Product(null, "Instant noodles, meat flavored-Predilecta ", "Instant pasta based on flours,cereals and starches", 1.99, "");
		Product p4 = new Product(null, "Nivea men Silver Protect", "Antitranspirante Aerossol Antibacteriano 150ml", 10.47, "");
		Product p5 = new Product(null, "Creme Dental menta original Colgate Tripla Açao 180g", "Preparo para higiene bucal", 4.98, "");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat2);
		p3.getCategories().add(cat1);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat3);
		
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		
		
		User u1 = new User(null, "Maria Brown", "mariaB@gmail.com", "11991234500", "@der3456");
		User u2 = new User(null, "Johnny Bravo", "johnnyB@gmail.com", "13994251942", "123456q");
		
		Order o1 = new Order(null, Instant.parse("2024-04-14T23:15:07Z"), OrderStatus.PAID, u2);
		Order o2 = new Order(null, Instant.parse("2024-04-14T23:17:23Z"), OrderStatus.WAITING_PAYMENT, u1);
		Order o3 = new Order(null, Instant.parse("2024-04-14T23:21:55Z"), OrderStatus.WAITING_PAYMENT ,u2);
		
		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
	}

}
