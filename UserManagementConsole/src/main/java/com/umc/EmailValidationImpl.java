package com.umc;

import org.springframework.stereotype.Component;

import com.umc.dao.EmailValidation;

@Component
public class EmailValidationImpl implements EmailValidation {

	@Override
	public boolean isValid(String email) {
		
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	    return email.matches(regex);
	}

}
