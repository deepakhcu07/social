package com.spgroup.friend.api.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.spgroup.friend.api.dto.response.UserResponseDto;
import com.spgroup.friend.entity.UserEntity;

public class UserEntityMapper {

	public static UserResponseDto toDto(UserEntity entity) {
		if (entity == null) {
			return null;
		}
		UserResponseDto dto = new UserResponseDto();
		dto.setEmailId(entity.getEmailId());
		dto.setName(entity.getName());
		return dto;
	}

	public static List<UserResponseDto> toDtoList(List<UserEntity> entities) {
		if (CollectionUtils.isEmpty(entities)) {
			return Collections.emptyList();
		}
		return entities.stream().map(UserEntityMapper::toDto).collect(Collectors.toList());
	}
}
