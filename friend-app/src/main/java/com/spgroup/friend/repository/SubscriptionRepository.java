package com.spgroup.friend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spgroup.friend.entity.SubscriptionEntity;
import com.spgroup.friend.entity.SubscriptionPk;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, SubscriptionPk> {

}
