package com.spgroup.friend.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spgroup.friend.api.dto.request.UserRequestDto;
import com.spgroup.friend.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/users")
@Api(value="User Management API",tags= {"User Management API"})
public class UserController {
	@Autowired
	private UserService userService;

	@ApiOperation(value = "Create a new User", response = String.class)
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully Created"),
            @ApiResponse(code = 400, message = "Bad Request")
    }
    )
	@RequestMapping(method=RequestMethod.POST)
	public String create(@RequestBody UserRequestDto user) {
		userService.create(user);
		return "SUCCESS";
	}
	
	@ApiOperation(value = "Create List of Users", response = String.class)
	@RequestMapping(value= "/bulk-create",method = RequestMethod.POST)
	public String create(@RequestBody List<UserRequestDto> users) {
		userService.create(users);
		return "SUCCESS";
	}
	
	@ApiOperation(value = "Generate Dummy Users", response = String.class)
	@RequestMapping(value="/dummy-data", method = RequestMethod.POST)
	public String createDummyUser() {
		List<UserRequestDto> users = generateUsers();
		userService.create(users);
		return "SUCCESS";
	}
	
	private List<UserRequestDto> generateUsers(){
		String abc = "abc";
		String xyz = "xyz";
		String email = "@example.com";
		List<UserRequestDto> result = new ArrayList<>();
		for(int i=1;i<=10;i++) {
			UserRequestDto user = new UserRequestDto();
			user.setEmail(abc+i+email);
			user.setName(abc+i);
			result.add(user);
		}
		
		for(int i=1;i<=10;i++) {
			UserRequestDto user = new UserRequestDto();
			user.setEmail(xyz+i+email);
			user.setName(xyz+i);
			result.add(user);
		}
		return result;
	}
}
