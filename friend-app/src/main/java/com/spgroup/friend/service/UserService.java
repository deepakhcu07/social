package com.spgroup.friend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spgroup.friend.api.dto.request.UserRequestDto;
import com.spgroup.friend.api.util.ValidatorComponent;
import com.spgroup.friend.entity.UserEntity;
import com.spgroup.friend.exception.UserNotFoundException;
import com.spgroup.friend.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ValidatorComponent validator;
	
	public void create(UserRequestDto user) {
		
		validator.validateEmail(user.getEmail());
		
		UserEntity entity = new UserEntity();
		entity.setEmailId(user.getEmail());
		entity.setName(user.getName());
		userRepository.save(entity);
	}
	
	public void  validateUser(String emailId) {
		UserEntity user = userRepository.findById(emailId).orElse(null);
		if(user==null) {
			throw new UserNotFoundException(emailId + " does not exist!");
		}
	}
	
	

}
