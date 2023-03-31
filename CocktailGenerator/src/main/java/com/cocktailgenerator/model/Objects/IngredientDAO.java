package com.cocktailgenerator.model.Objects;

import org.bson.Document;

import com.cocktailgenerator.main.DataConnection;
import com.mongodb.client.MongoCollection;

public class IngredientDAO {
	
	private DataConnection datCon;
	private MongoCollection<Document> ingredientsCOL;
	
	public IngredientDAO(String collectionName) {
		ingredientsCOL = datCon.getDB().getCollection(collectionName);
	}

}
