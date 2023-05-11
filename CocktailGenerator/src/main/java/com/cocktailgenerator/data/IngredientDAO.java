package com.cocktailgenerator.data;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class IngredientDAO {
	
	private DataConnection datCon;
	private MongoCollection<Document> ingredientsCOL;
	
	private IngredientDAO(String collectionName) {
		ingredientsCOL = datCon.getDB().getCollection(collectionName);
	}
	
	/*
	 * Look into Spring repository pattern. Consider two repository classes;
	 * One for the masterList, and another that builds for a specific user name
	 * 
	 * A lot of what Drink generator does now is hold the lists, so perhaps I should
	 * bust out the EnumMap into a repository class. Than perhaps one repository 
	 * will extend the other?
	 * Turns out MongoRepository is a thing, that might bet he way to go here.
	 * Alternatively, what I might ACUTALLY want is to do something with Spring Context. 
	 * The DrinkGenerator is really the meat of the context, and does really need to 
	 * be a Repository
	 */
}
