package com.modelmono.appwholesale.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modelmono.appwholesale.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
