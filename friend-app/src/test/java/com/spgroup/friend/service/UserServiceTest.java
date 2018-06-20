package com.spgroup.friend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.spgroup.friend.api.dto.request.UserRequestDto;
import com.spgroup.friend.entity.UserEntity;
import com.spgroup.friend.exception.InvalidDataException;
import com.spgroup.friend.exception.UserNotFoundException;
import com.spgroup.friend.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	private List<UserEntity> users = new ArrayList<>();

	@MockBean
	private UserRepository mockUserRepository;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void createWillThrowInvalidaDataExceptionIfEmailIsInvalid() {
		thrown.expect(InvalidDataException.class);
		UserRequestDto user = new UserRequestDto();
		user.setEmail("asdasdad");
		user.setName("ddd");
		
		userService.create(user);
		
	}
	
	@Test
	public void validateUserWillThrowUserNotFoundExceptionIfThereIsNoUser() {
		thrown.expect(UserNotFoundException.class);
		String email="abc@example.com";
		Optional<UserEntity> user = Optional.empty();
		Mockito.when(mockUserRepository.findById(email)).thenReturn(user);
		userService.validateUser(email);
	}
	
}
