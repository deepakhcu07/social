package com.spgroup.friend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spgroup.friend.entity.SubscriptionEntity;
import com.spgroup.friend.entity.SubscriptionPk;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, SubscriptionPk> {

	@Query(value = 
			"SELECT REQUESTOR_EMAIL_ID AS RECIPIENT FROM SUBSCRIPTIONS s WHERE s.TARGET_EMAIL_ID = :sender "
			+ "and s.block = 'false'"
			+ " UNION "
			+ "SELECT FRIEND_EMAIL_ID AS RECIPIENT FROM FRIENDS f where f.USER_EMAIL_ID = :sender and "
			+ "f.FRIEND_EMAIL_ID not in "
			+ "(SELECT REQUESTOR_EMAIL_ID FROM SUBSCRIPTIONS t WHERE t.TARGET_EMAIL_ID = :sender "
			+ "and t.block = 'true')",
			nativeQuery = true)
	List<String> getRecipients(@Param("sender") String sender); 
	
	@Query(value = 
			"SELECT REQUESTOR_EMAIL_ID AS RECIPIENT FROM SUBSCRIPTIONS s WHERE s.TARGET_EMAIL_ID = :sender "
			+ "and s.block = 'true'",nativeQuery = true)
	List<String> getBlockUser(@Param("sender") String sender);
}
