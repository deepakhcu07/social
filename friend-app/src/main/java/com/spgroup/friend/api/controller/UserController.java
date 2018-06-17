package com.spgroup.friend.api.controller;

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
}
