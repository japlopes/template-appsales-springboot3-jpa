package com.modelmono.appwholesale.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modelmono.appwholesale.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
