package com.spgroup.friend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spgroup.friend.api.dto.request.FriendRequestDto;
import com.spgroup.friend.api.dto.request.SearchFriendDto;
import com.spgroup.friend.api.dto.response.FriendListResponseDto;
import com.spgroup.friend.api.dto.response.SuccessResponseDto;
import com.spgroup.friend.service.FriendService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/friends")
@Api(value="Friend Management API",tags= {"Friend Management API"})
public class FriendController {
	
	@Autowired
	private FriendService friendService;
	
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
	
	
	@ApiOperation(value = "Create a new Friend Connection", response = SuccessResponseDto.class)
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully Created a new Connection"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "User Not found"),
            @ApiResponse(code = 409, message = "Friend Connection already exists"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    }
    )
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<SuccessResponseDto> create(@RequestBody FriendRequestDto friendRequest) {
		friendService.connectFriend(friendRequest);
		
		SuccessResponseDto response = new SuccessResponseDto();
		response.setSuccess(true);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Retrieve Friend List of given Email Address", response = FriendListResponseDto.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Retrieved Email List of given EMail Address"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    }
    )
	@RequestMapping(value="/search", method = RequestMethod.POST)
	public ResponseEntity<FriendListResponseDto> getFriendList(@RequestBody SearchFriendDto searchDto) {
		
		FriendListResponseDto result = friendService.getFriends(searchDto.getEmail());
		return new ResponseEntity<>(result, HttpStatus.OK);
		
	}

}
