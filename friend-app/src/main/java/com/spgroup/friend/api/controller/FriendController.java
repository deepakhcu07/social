package com.spgroup.friend.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/friends")
@Api(value="Friend Management API",tags= {"Friend Management API"})
public class FriendController {
	
	@ApiOperation(value = "Create a new Friend Connection", response = String.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tested Successfully"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    }
    )
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "Tested Successfully";
	}

}
