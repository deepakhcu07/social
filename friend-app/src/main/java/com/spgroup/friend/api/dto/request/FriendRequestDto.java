package com.spgroup.friend.api.dto.request;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class FriendRequestDto {

	@ApiModelProperty(notes = "The size of the list must be equal two.")
	private List<String> friends;

	public List<String> getFriends() {
		return friends;
	}

	public void setFriends(List<String> friends) {
		this.friends = friends;
	}
}
