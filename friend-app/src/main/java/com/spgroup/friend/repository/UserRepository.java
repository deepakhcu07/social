package com.spgroup.friend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spgroup.friend.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>  {

}
