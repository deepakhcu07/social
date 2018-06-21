package com.spgroup.friend.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUtil {
	
	//Added Private Constructor to restrict instantiation of this class
	private EmailUtil() {
		
	}

	private static final String REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

	private static final Pattern pattern = Pattern.compile(REGEX);
	private static final Pattern findEmailPattern = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");

	public static boolean isValidEmail(String email) {
		return pattern.matcher(email).matches();
	}
	
	public static List<String> getAllMentionedEmail(String text){
		List<String> result = new ArrayList<>();
		
		Matcher m = findEmailPattern.matcher(text);
		while(m.find()) {
			result.add(m.group());
		}
		return result;
	}


}
