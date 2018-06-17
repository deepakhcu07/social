package com.spgroup.friend.api.dto.request;

import io.swagger.annotations.ApiModelProperty;

public class UserRequestDto {

	@ApiModelProperty(notes="Email Id will be user id")
	private String email;
	@ApiModelProperty
	private String name;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
