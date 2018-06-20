package com.spgroup.friend.api.dto.response;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class ErrorrResponseDto {

	@ApiModelProperty
	private boolean success;
	@ApiModelProperty
	private Date timestamp;
	
	@ApiModelProperty
	private String message;
	
	@ApiModelProperty
	private String details;
	
	public ErrorrResponseDto(Date timestamp,String message, String details) {
		this.timestamp = timestamp;
		this.message= message;
		this.details = details;
		this.success = false;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public boolean isSuccess() {
		return success;
	}
	
}
