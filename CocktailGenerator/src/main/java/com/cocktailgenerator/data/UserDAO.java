package com.cocktailgenerator.data;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

public class UserDAO {

	private DataConnection datCon;
	private MongoCollection<Document> usersCOL;
	
	public UserDAO() {
		datCon = DataConnection.getInstance();
		usersCOL = datCon.getDB().getCollection("Users");
	}
	
	public void AddUser(String userName, String hashedPass) {
		
		usersCOL.insertOne(new Document().append("userName", userName)
				.append("password", hashedPass));
	}
	
	public String retrieveHash(String userName) {
		
		Bson filter = Filters.eq("userName", userName);
		Bson projectionFields = Projections.fields(Projections.excludeId());
		
		FindIterable<Document> userDoc = usersCOL.find(filter).projection(projectionFields);
		return userDoc.first().get("password").toString();
	}
	
	public Boolean userExists(String userName) {
		
		Bson filter = Filters.eq("userName", userName);
		FindIterable<Document> userDoc = usersCOL.find(filter);
		
		System.out.println ("userDoc.first is null? " + userDoc.first() == null);
		
		if ( userDoc.first() != null && userDoc.first().toString().equals(userName)) {
			return true;
		}
		else {
			return false;
		}
	}
}






