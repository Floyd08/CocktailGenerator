package com.cocktailgenerator.controller;

import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cocktailgenerator.main.DataConnection;
import com.cocktailgenerator.model.Objects.DrinkGenerator;
import com.cocktailgenerator.model.Objects.User;
import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = {"${CrossOriginValue}"})
public class SessionController {

	DataConnection datCon;
	MongoCollection<Document> userCOL;
	
	
	public SessionController() {}
	
	@GetMapping("/Guest")
	public void newGuest(HttpSession session) {
		session.setAttribute("drinkGenerator", new DrinkGenerator());
	}
	
//	@GetMapping("/getUser")
//	public User validateUser(String name, String pswHash) {
//		
//		FindIterable<Document> userIterable = userCOL.find(eq("name", name));
//		Document userDoc = userIterable.first();
//		User user = gS.fromJson(userDoc.toJson(), User.class);
//		
//		
//	}
}
