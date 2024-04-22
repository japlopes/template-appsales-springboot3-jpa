package com.modelmono.appwholesale.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.modelmono.appwholesale.entities.Category;
import com.modelmono.appwholesale.entities.Order;
import com.modelmono.appwholesale.entities.OrderItem;
import com.modelmono.appwholesale.entities.Payment;
import com.modelmono.appwholesale.entities.Product;
import com.modelmono.appwholesale.entities.User;
import com.modelmono.appwholesale.entities.enums.OrderStatus;
import com.modelmono.appwholesale.entities.enums.PaymentStatus;
import com.modelmono.appwholesale.repositories.CategoryRepository;
import com.modelmono.appwholesale.repositories.OrderItemRepository;
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

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Foods");
		Category cat2 = new Category(null, "Drinks");
		Category cat3 = new Category(null, "Hygiene and Beauty");

		Product p1 = new Product(null, "Kero Coconut", "Fruit or vegetable, juice mix", 3.79, "");
		Product p2 = new Product(null, "Toddy prisma 200", "Ready-made lacteal chocolate drink", 4.89, "");
		Product p3 = new Product(null, "Instant noodles, meat flavored-Predilecta ",
				"Instant pasta based on flours,cereals and starches", 1.99, "");
		Product p4 = new Product(null, "Nivea men Silver Protect", "Antitranspirante Aerossol Antibacteriano 150ml",
				10.47, "");
		Product p5 = new Product(null, "Creme Dental menta original Colgate Tripla Açao 180g",
				"Preparo para higiene bucal", 4.98, "");

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		p1.getCategories().add(cat2);
		p2.getCategories().add(cat2);
		p3.getCategories().add(cat1);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat3);

		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		User u1 = new User(null, "Maria Brown", "mariaB@gmail.com", "11991234500", "@der3456");
		User u2 = new User(null, "Johnny Bravo", "johnnyB@gmail.com", "13994251942", "123456q");

		Order o1 = new Order(null, Instant.parse("2024-04-14T23:15:07Z"), OrderStatus.PAID, u2);
		Order o2 = new Order(null, Instant.parse("2024-04-14T23:17:23Z"), OrderStatus.WAITING_PAYMENT, u1);
		Order o3 = new Order(null, Instant.parse("2024-04-14T23:21:55Z"), OrderStatus.PACKING, u2);

		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));

		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o2, p2, 6, p2.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 3, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p2, 4, p2.getPrice());
		OrderItem oi5 = new OrderItem(o3, p3, 2, p3.getPrice());
		OrderItem oi6 = new OrderItem(o3, p4, 1, p4.getPrice());
		OrderItem oi7 = new OrderItem(o3, p5, 1, p5.getPrice());

		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4, oi5, oi6, oi7));

		Payment pay1 = new Payment(null, Instant.parse("2024-04-14T01:15:07Z"), PaymentStatus.PAID, o1);
		o1.setPayment(pay1);

		orderRepository.save(o1);

	}

}
