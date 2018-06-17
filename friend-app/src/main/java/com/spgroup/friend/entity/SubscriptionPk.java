package com.spgroup.friend.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class SubscriptionPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3681302181716206847L;
	
	private String requestorEmailId;
	private String targetEmailId;
	
	
	public String getRequestorEmailId() {
		return requestorEmailId;
	}
	public void setRequestorEmailId(String requestorEmailId) {
		this.requestorEmailId = requestorEmailId;
	}
	public String getTargetEmailId() {
		return targetEmailId;
	}
	public void setTargetEmailId(String targetEmailId) {
		this.targetEmailId = targetEmailId;
	}
	

}
