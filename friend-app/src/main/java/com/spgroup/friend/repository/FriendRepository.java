package com.spgroup.friend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spgroup.friend.entity.FriendEntity;
import com.spgroup.friend.entity.FriendPk;

public interface FriendRepository extends JpaRepository<FriendEntity, FriendPk> {

}
