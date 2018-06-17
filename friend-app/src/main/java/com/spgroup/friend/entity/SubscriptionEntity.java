/**
 * 
 */
package com.spgroup.friend.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Deepak
 *
 */
@Entity
@Table(name="SUBSCRIPTIONS")
public class SubscriptionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3109374613941570309L;
	
	@EmbeddedId
	private SubscriptionPk pk;
	private boolean block;
	
	public SubscriptionPk getPk() {
		return pk;
	}
	public void setPk(SubscriptionPk pk) {
		this.pk = pk;
	}
	public boolean isBlock() {
		return block;
	}
	public void setBlock(boolean block) {
		this.block = block;
	}

}
