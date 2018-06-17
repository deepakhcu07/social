package com.spgroup.friend.api.dto.response;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @author deepak
 *
 */
public class RecipientResponseDto {
	@ApiModelProperty
	private boolean success;
	@ApiModelProperty(notes="Recipients of text message")
	private List<String> recipients;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<String> getRecipients() {
		return recipients;
	}
	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}
}
