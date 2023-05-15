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
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = {"${CrossOriginValue}"})
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
			
			if ( !userDAO.userExists(userName) ) {
				userDAO.AddUser(userName, hashPass(password));
				return 1;
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
	
	@PostMapping("/authenticate")
	public int authenticate(@RequestBody String userJSON) {
		
		try {
			JsonNode newUser = mapper.readTree(userJSON);
			String userName = newUser.findValue("userName").asText();
			String password = newUser.findValue("password").asText();
			
			if ( hashIt.encoder().matches(password, userDAO.retrieveHash(userName)) ) {
				return 1;
			}
			return 0;			
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
}
