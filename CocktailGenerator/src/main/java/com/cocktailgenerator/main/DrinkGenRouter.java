package com.cocktailgenerator.main;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class DrinkGenRouter {
	
	private HashMap<String, DrinkGenerator> activeGenerators;
	
	public DrinkGenRouter() {
		activeGenerators = new HashMap<String, DrinkGenerator>();
	}
	
	public DrinkGenerator getGuest() {
		
		initializeGuest();
		return activeGenerators.get("Guest");
	}
	
	public void initializeGuest() {
		
		if ( !activeGenerators.containsKey("Guest") ) {
			activeGenerators.put("Guest", new DrinkGenerator());
		}
	}
	
	public DrinkGenerator getUser(String userName) {
		
		if (userName.equals("Guest")) {
			return getGuest();
		}
		else {
			
			if ( !activeGenerators.containsKey(userName) ) {
				activeGenerators.put(userName, new DrinkGenerator(userName));
			} 
			return activeGenerators.get(userName);
		}
	}
	
	public void initializeUser(String userName) {
		
		if ( !activeGenerators.containsKey(userName) ) {
			activeGenerators.put(userName, new DrinkGenerator(userName));
		}
	}
}
