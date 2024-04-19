package com.modelmono.appwholesale.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modelmono.appwholesale.entities.Product;
import com.modelmono.appwholesale.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	public Product findById(Long idProduct) {
		Optional<Product> obj = productRepository.findById(idProduct);
		return obj.get();
		
	}

}
