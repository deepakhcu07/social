package com.spgroup.friend.api.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class SuccessResponseDto {

	@ApiModelProperty
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
		
}
