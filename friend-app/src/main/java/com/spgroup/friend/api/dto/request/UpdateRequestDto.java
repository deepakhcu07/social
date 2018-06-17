package com.spgroup.friend.api.dto.request;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @author deepak
 *
 */
public class UpdateRequestDto {
	@ApiModelProperty(notes="Sender Email Id")
	private String sender;
	@ApiModelProperty(notes=" Text Message")
	private String text;
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}
