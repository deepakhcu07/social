package com.spgroup.friend.api.util;

import org.springframework.stereotype.Component;

import com.spgroup.friend.exception.InvalidDataException;
import com.spgroup.friend.util.EmailUtil;

@Component
public class ValidatorComponent {
	
	public void validateEmail(String email) {
		if(email==null || email.length()==0) {
			throw new InvalidDataException("email cannot be null or empty.");
		}
		
		if(!EmailUtil.isValidEmail(email)) {
			throw new InvalidDataException(email + " is not a valid email.");
		}
	}

}
