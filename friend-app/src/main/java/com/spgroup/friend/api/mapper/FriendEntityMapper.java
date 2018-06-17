package com.spgroup.friend.api.mapper;

import com.spgroup.friend.api.dto.request.FriendRequestDto;
import com.spgroup.friend.entity.FriendEntity;
import com.spgroup.friend.entity.FriendPk;

public class FriendEntityMapper {

	public static FriendEntity toEntity(FriendRequestDto friendRequest) {
		FriendEntity entity = new FriendEntity();
		FriendPk pk = new FriendPk();
		String userEmailId = friendRequest.getFriends().get(0);
		String friendEmailId = friendRequest.getFriends().get(1);
		pk.setUserEmailId(userEmailId);
		pk.setFriendEmailId(friendEmailId);
		entity.setPk(pk);
		return entity;
				
	}
}
