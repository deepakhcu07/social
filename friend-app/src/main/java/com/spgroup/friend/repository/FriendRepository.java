package com.spgroup.friend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spgroup.friend.entity.FriendEntity;
import com.spgroup.friend.entity.FriendPk;

public interface FriendRepository extends JpaRepository<FriendEntity, FriendPk> {

	public List<FriendEntity> findByPkUserEmailId(String emailId);
}
