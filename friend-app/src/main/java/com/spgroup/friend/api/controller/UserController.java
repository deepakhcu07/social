package com.spgroup.friend.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spgroup.friend.api.dto.request.UserRequestDto;
import com.spgroup.friend.api.dto.response.SuccessResponseDto;
import com.spgroup.friend.api.dto.response.UserResponseDto;
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

	@ApiOperation(value = "Create a new User", response = SuccessResponseDto.class)
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully Created"),
            @ApiResponse(code = 400, message = "Bad Request")
    }
    )
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<SuccessResponseDto> create(@RequestBody UserRequestDto user) {
		userService.create(user);
		SuccessResponseDto response = new SuccessResponseDto();
		response.setSuccess(true);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Return all users", response = String.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    }
    )
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserResponseDto>> getAll() {
		List<UserResponseDto> result = userService.getAllUsers();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Create List of Users", response = SuccessResponseDto.class)
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully Created"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    }
    )
	@RequestMapping(value= "/bulk-create",method = RequestMethod.POST)
	public ResponseEntity<SuccessResponseDto>  create(@RequestBody List<UserRequestDto> users) {
		userService.create(users);
		SuccessResponseDto response = new SuccessResponseDto();
		response.setSuccess(true);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Generate Dummy Users", response = String.class)
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully Created"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    }
    )
	@RequestMapping(value="/dummy-data", method = RequestMethod.POST)
	public ResponseEntity<SuccessResponseDto> createDummyUser() {
		List<UserRequestDto> users = generateUsers();
		userService.create(users);
		SuccessResponseDto response = new SuccessResponseDto();
		response.setSuccess(true);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
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
