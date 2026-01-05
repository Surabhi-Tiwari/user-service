package com.example.service;

import java.util.List;

import com.example.entity.User;

public interface UserService {
	
	// create 
	User saveUser(User user);
	
	// get All User
	
	 List<User>getAllUser();
	 
	 // getby id
	 
	 User getByIdUser(String userId);
	 
	 // delete by id 
	 
	 void deleteByUserId(String userId); 
	 
	 // update 
	 
	 User updateUser(String userId, User user);

	
	 
	 // here -> userId → which user to update
	 // User user → updated data

}
