package com.spgroup.friend.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friends")
public class FriendController {
	
	@RequestMapping("/test")
	public String test() {
		return "Tested Successfully";
	}

}
