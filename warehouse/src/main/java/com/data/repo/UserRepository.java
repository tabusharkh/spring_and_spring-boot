package com.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUsernameAndPassword(String username, String password);

}
