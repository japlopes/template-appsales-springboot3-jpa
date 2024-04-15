package com.modelmono.appwholesale.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modelmono.appwholesale.entities.Order;
import com.modelmono.appwholesale.services.OrderService;

@RestController
@RequestMapping(value="/orders")
public class OrderResource {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> list = orderService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{idOrder}")
	public ResponseEntity<Order> findById(@PathVariable Long idOrder){
		Order obj = orderService.findById(idOrder);
		return ResponseEntity.ok().body(obj);
		
	}
	

}
