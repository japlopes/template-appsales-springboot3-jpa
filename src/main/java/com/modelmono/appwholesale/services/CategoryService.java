package com.modelmono.appwholesale.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modelmono.appwholesale.entities.Category;
import com.modelmono.appwholesale.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}
	
	public Category findById(Long idCateg) {
		Optional<Category> obj = categoryRepository.findById(idCateg);
		return obj.get();
		
	}

}
