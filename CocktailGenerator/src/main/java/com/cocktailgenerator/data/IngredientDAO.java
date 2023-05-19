package com.cocktailgenerator.data;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.cocktailgenerator.entity.Ingredient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class IngredientDAO {
	
	private DataConnection datCon;
	private MongoCollection<Document> userIngredientsCOL;
	
	public IngredientDAO() {
		datCon = DataConnection.getInstance();
		userIngredientsCOL = datCon.getDB().getCollection("UserIngredients");
	}
	
	public void AddUserIngredient(Ingredient ingredient, String userName) {
		
		userIngredientsCOL.insertOne(new Document().append("owner", userName)
				.append("superType", ingredient.getSuperType())
				.append("type", ingredient.getType())
				.append("subType", ingredient.getSubType())
				.append("proportion", 0));
	}
	
	public void removeUserIngredient(String subType, String userName) {
		
		Bson filter = Filters.and(Filters.eq("owner", userName), Filters.eq("subType", subType));
		userIngredientsCOL.deleteOne(filter);
	}
}
