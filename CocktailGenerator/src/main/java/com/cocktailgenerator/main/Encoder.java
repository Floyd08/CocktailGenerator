package com.cocktailgenerator.main;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Encoder {

	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
