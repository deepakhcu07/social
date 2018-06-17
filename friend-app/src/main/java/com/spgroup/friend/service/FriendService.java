package com.spgroup.friend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spgroup.friend.api.dto.request.FriendRequestDto;
import com.spgroup.friend.api.mapper.FriendEntityMapper;
import com.spgroup.friend.api.util.ValidatorComponent;
import com.spgroup.friend.entity.FriendEntity;
import com.spgroup.friend.entity.FriendPk;
import com.spgroup.friend.exception.ResourceAlreadyExistException;
import com.spgroup.friend.repository.FriendRepository;

@Service
public class FriendService {
	@Autowired
	private FriendRepository friendRepository;
	
	@Autowired
	private UserService userService;

	@Autowired
	private ValidatorComponent validator;

	public void connectFriend(FriendRequestDto friendRequest) {
		
		validate(friendRequest);
		
		FriendEntity entity = FriendEntityMapper.toEntity(friendRequest);
		friendRepository.save(entity);

		// Swap the id
		FriendPk pk = entity.getPk();
		String temp = pk.getUserEmailId();
		pk.setUserEmailId(pk.getFriendEmailId());
		pk.setFriendEmailId(temp);

		FriendEntity entity2 = new FriendEntity();
		entity2.setPk(pk);
		friendRepository.save(entity);
	}
	
	
	private void validate(FriendRequestDto friendRequest) {
		// Validating Request Dto
		validator.validateFriendRequestDto(friendRequest);
		// Validate User Exist or Not
		userService.validateUser(friendRequest.getFriends().get(0));
		userService.validateUser(friendRequest.getFriends().get(1));
		//Check if connection already there
		FriendEntity entity = FriendEntityMapper.toEntity(friendRequest);
		if(friendRepository.existsById(entity.getPk())) {
			throw new ResourceAlreadyExistException("Users are already connected as a friend");
		}
		
	}

}
