package com.spgroup.friend.util;

import java.util.regex.Pattern;

public class EmailUtil {

	private static final String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

	private static final Pattern pattern = Pattern.compile(regex);

	public static boolean isValidEmail(String email) {
		return pattern.matcher(email).matches();
	}


}
