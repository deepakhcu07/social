package com.spgroup.friend.api.dto.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author Deepak
 *
 */
public class SubscribeRequestDto {

	@ApiModelProperty(notes= "Requestor Email Address")
	private String requestor;
	
	@ApiModelProperty(notes= "Target Email Address")
	private String target;
	
	
	public String getRequestor() {
		return requestor;
	}
	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
}
