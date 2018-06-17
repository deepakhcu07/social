package com.spgroup.friend.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author Deepak
 *
 */
@Entity
@Table(name="FRIENDS")
public class FriendEntity implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9210112770153910852L;
	
	@EmbeddedId
	private FriendPk pk;

	public FriendPk getPk() {
		return pk;
	}

	public void setPk(FriendPk pk) {
		this.pk = pk;
	}
	
	

}
