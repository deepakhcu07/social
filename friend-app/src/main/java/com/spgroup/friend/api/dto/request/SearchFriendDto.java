package com.spgroup.friend.api.dto.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author Deepak
 *
 */
public class SearchFriendDto {
	@ApiModelProperty
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
