package com.data.service;

import com.data.entity.User;

public interface UserService {
	
	User findByUsernameAndPassword(String username, String password);

}
