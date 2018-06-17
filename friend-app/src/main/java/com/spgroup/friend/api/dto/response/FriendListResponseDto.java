package com.spgroup.friend.api.dto.response;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class FriendListResponseDto {
	@ApiModelProperty
	private boolean success;
	
	@ApiModelProperty (notes = "List of Friends")
	private List<String> friends;
	
	@ApiModelProperty(notes = "Number of Friends")
	private int count;
	
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<String> getFriends() {
		return friends;
	}
	public void setFriends(List<String> friends) {
		this.friends = friends;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
