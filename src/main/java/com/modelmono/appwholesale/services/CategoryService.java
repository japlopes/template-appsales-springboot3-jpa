package com.modelmono.appwholesale.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.modelmono.appwholesale.entities.Category;
import com.modelmono.appwholesale.repositories.CategoryRepository;
import com.modelmono.appwholesale.services.exceptions.DatabaseException;
import com.modelmono.appwholesale.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}
	
	public Category findById(Long idCateg) {
		Optional<Category> obj = categoryRepository.findById(idCateg);
		return obj.orElseThrow(()-> new ResourceNotFoundException(idCateg));
		
	}
	
	public Category insert(Category obj) {
		return categoryRepository.save(obj);
	}
	
	public void delete(Long idCateg) {
		try {
			categoryRepository.deleteById(idCateg);
		}catch(EmptyResultDataAccessException e){
			throw new ResourceNotFoundException(idCateg);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Category update(Long idCateg, Category obj) {
		try {
			Category entity = categoryRepository.getReferenceById(idCateg);
			updateData(entity, obj);
			return categoryRepository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(idCateg);
		}
	}
	
	private void updateData(Category entity, Category obj) {
		entity.setName(obj.getName());
	}

}
