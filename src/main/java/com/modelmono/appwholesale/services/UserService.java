package com.modelmono.appwholesale.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modelmono.appwholesale.entities.User;
import com.modelmono.appwholesale.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(Long idUser) {
		Optional<User> obj = userRepository.findById(idUser);
		return obj.get();
		
	}

}
