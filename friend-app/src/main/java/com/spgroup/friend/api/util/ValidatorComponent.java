package com.spgroup.friend.api.util;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.spgroup.friend.api.dto.request.FriendRequestDto;
import com.spgroup.friend.exception.InvalidDataException;
import com.spgroup.friend.util.EmailUtil;

@Component
public class ValidatorComponent {
	
	public void validateEmail(String email) {
		if(StringUtils.isEmpty(email)) {
			throw new InvalidDataException("email cannot be null or empty.");
		}
		
		if(!EmailUtil.isValidEmail(email)) {
			throw new InvalidDataException(email + " is not a valid email.");
		}
	}
	
	public void validateFriendRequestDto(FriendRequestDto friendRequest) {
		List<String> friends = friendRequest.getFriends();
		if(CollectionUtils.isEmpty(friends)) {
			throw new InvalidDataException("friends Cannot be null or empty.");
		}
		if(friends.size()!=2) {
			throw new InvalidDataException("Size of friends must be equal to two.");
		}
		
		validateEmail(friendRequest.getFriends().get(0));
		validateEmail(friendRequest.getFriends().get(1));
	}

}
