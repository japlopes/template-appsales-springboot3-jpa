package com.modelmono.appwholesale.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.modelmono.appwholesale.entities.User;
import com.modelmono.appwholesale.repositories.UserRepository;
import com.modelmono.appwholesale.services.exceptions.DatabaseException;
import com.modelmono.appwholesale.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(Long idUser) {
		Optional<User> obj = userRepository.findById(idUser);
		return obj.orElseThrow(()-> new ResourceNotFoundException(idUser));
		
	}
	
	public User insert(User obj) {
		return userRepository.save(obj);
	}
	
	public void delete(long idUser) {
		try {
			userRepository.deleteById(idUser);
		}catch(EmptyResultDataAccessException e){
			throw new ResourceNotFoundException(idUser);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public User update(Long idUser, User obj) {
		User entity = userRepository.getReferenceById(idUser);
		updateData(entity, obj);
		return userRepository.save(entity);
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
	}

}
