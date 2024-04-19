package com.modelmono.appwholesale.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modelmono.appwholesale.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
