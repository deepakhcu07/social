package com.spgroup.friend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spgroup.friend.api.dto.request.UserRequestDto;
import com.spgroup.friend.entity.UserEntity;
import com.spgroup.friend.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public void create(UserRequestDto user) {
		UserEntity entity = new UserEntity();
		entity.setEmailId(user.getEmail());
		entity.setName(user.getName());
		userRepository.save(entity);
	}

}
