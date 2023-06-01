package com.cocktailgenerator.main;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class DrinkGenRouter {
	
	@Autowired
	private CacheManager cacheManager;
	
	//private HashMap<String, DrinkGenerator> activeGenerators;
	
	public DrinkGenRouter() {
		//activeGenerators = new HashMap<String, DrinkGenerator>();
	}
	
//	public DrinkGenerator getGuest() {
//		
//		initializeGuest();
//		return activeGenerators.get("Guest");
//	}
	
//	public void initializeGuest() {
//		
//		if ( !activeGenerators.containsKey("Guest") ) {
//			activeGenerators.put("Guest", new DrinkGenerator());
//		}
//	}
	
	@Cacheable("DrinkGenerator")
	public DrinkGenerator getUser(String userName) {
		
		if (userName.equals("Guest")) {
			return new DrinkGenerator();
		}
		else {
			return new DrinkGenerator(userName);
		}
	}
	
//	public DrinkGenerator getUser(String userName) {
//		
//		if (userName.equals("Guest")) {
//			return getGuest();
//		}
//		else {
//			initializeUser(userName);
//			return activeGenerators.get(userName);
//		}
//	}
	
//	public void initializeUser(String userName) {
//		
//		if ( !activeGenerators.containsKey(userName) ) {
//			activeGenerators.put(userName, new DrinkGenerator(userName));
//		}
//	}
	
	public void reinitializeUser(String userName) {
		//activeGenerators.put(userName, new DrinkGenerator(userName));
		Cache cache = cacheManager.getCache(userName);
		if (cache != null) {
			cache.clear();
		}
	}
	
	
}
