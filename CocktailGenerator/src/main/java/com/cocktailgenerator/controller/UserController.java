package com.cocktailgenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cocktailgenerator.data.UserDAO;
import com.cocktailgenerator.main.Encoder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = {"${CrossOriginValue}"})
public class UserController {

	@Autowired
	private Encoder hashIt;
	
	private UserDAO userDAO;
	private ObjectMapper mapper;
	
	public UserController() {
		userDAO = new UserDAO();
		mapper = new ObjectMapper();
	}
	
	@PostMapping("/register")
	public int registerUser(@RequestBody String userJSON) {
		
		try {
			JsonNode newUser = mapper.readTree(userJSON);
			String userName = newUser.findValue("userName").asText();
			String password = newUser.findValue("password").asText();
			
			if ( userDAO.userExists(userName) ) {
				return 0;
			}
			else if ( (userName.isEmpty() || userName.isBlank()) || (password.isEmpty() || password.isBlank()) ) {
				return 2;
			}
			else {
				userDAO.AddUser(userName, hashPass(password));
				return 1;
			}			
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return 0;
	}
	
	@PostMapping("/authenticate")
	public int authenticate(@RequestBody String userJSON) {
		
		try {
			JsonNode newUser = mapper.readTree(userJSON);
			String userName = newUser.findValue("userName").asText();
			String password = newUser.findValue("password").asText();
			
			String hash = userDAO.retrieveHash(userName);
			
			if ( hash != null && hashIt.encoder().matches(password, hash) ) {
				return 1;
			}
			else if ( hash == null) {
				return 2;
			}
			else {
				return 0;	
			}		
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return 0;
	}
	
	private String hashPass(String plainText) {
		return hashIt.encoder().encode(plainText);
	}
	
	private boolean validateUserName(String userName) {
		
		if (userName.isEmpty()) {
			return false;
		}
		return true;
	}
}
