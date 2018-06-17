/**
 * 
 */
package com.spgroup.friend.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Deepak
 *
 */
@Entity
@Table(name="USERS")
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -439879137996542276L;
	
	@Id
	private String emailId;
	private String name;
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
