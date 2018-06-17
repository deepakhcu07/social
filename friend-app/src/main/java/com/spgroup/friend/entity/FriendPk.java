package com.spgroup.friend.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class FriendPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6350153808678648612L;
	
	private String userEmailId;
	private String friendEmailId;
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public String getFriendEmailId() {
		return friendEmailId;
	}
	public void setFriendEmailId(String friendEmailId) {
		this.friendEmailId = friendEmailId;
	}
	
	

}
