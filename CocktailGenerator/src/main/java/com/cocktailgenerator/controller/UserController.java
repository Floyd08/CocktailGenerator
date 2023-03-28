package com.cocktailgenerator.controller;

import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cocktailgenerator.main.DataConnection;
import com.cocktailgenerator.model.Objects.User;
import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "http://18.191.37.210")
public class UserController {

	DataConnection datCon;
	MongoCollection<Document> userCOL;
	Gson gS = new Gson();
	
	public UserController() {
		
		datCon = DataConnection.getInstance();
		userCOL = datCon.getDB().getCollection("Users");
	}
	
	@GetMapping("/getUser")
	public User validateUser(String name, String pswHash) {
		
		FindIterable<Document> userIterable = userCOL.find(eq("name", name));
		Document userDoc = userIterable.first();
		User user = gS.fromJson(userDoc.toJson(), User.class);
		
		
	}
}
