package com.cocktailgenerator.Testing;

import java.util.ArrayList;
import java.util.Iterator;

import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.cocktailgenerator.data.DataConnection;
import com.cocktailgenerator.entity.Ingredient;
import com.cocktailgenerator.entity.Recipe;
import com.cocktailgenerator.entity.RecipeBook;
import com.cocktailgenerator.main.DrinkGenerator;
import com.cocktailgenerator.main.ingredientType;
import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

public class DB_Testing {

	public static void main(String[] args) {
		
		DataConnection datCon = DataConnection.getInstance();
		MongoCollection<Document> templateJSONs = datCon.getDB().getCollection("Templates");
		
		FindIterable<Document> templates = templateJSONs.find();		
		Iterator<Document> templIterator = templates.iterator();
		
//		while (templIterator.hasNext()) {
//			System.out.println(templIterator.next());
//		}
		
		MongoCollection<Document> ingredients = datCon.getDB().getCollection("UserIngredients");
		Bson projectionFields = Projections.fields(
				Projections.include("superType", "type", "subType", "proportion"), 
				Projections.excludeId());
		FindIterable<Document> userIngredients, subSetIngredients;
		Bson filter = Filters.eq("owner", "Floyd");
		subSetIngredients = ingredients.find(filter)				
				.projection(projectionFields);
		
		Iterator<Document> subSetErator = subSetIngredients.iterator();
		while (subSetErator.hasNext()) {
			System.out.println(subSetErator.next());
		}

//		MongoCollection<Document> ingredients = datCon.getDB().getCollection("Ingredients");
//		
//		Bson projectionFields = Projections.fields(
//				Projections.include("superType", "type", "subType", "proportion"), 
//				Projections.excludeId());
//		FindIterable<Document> dessertLiqueurs = ingredients.find(eq("type", "DessertLiqueur"))
//				.projection(projectionFields);
//		Iterator<Document> dessertLerator = dessertLiqueurs.iterator();
//		
//		while (dessertLerator.hasNext()) {
//			System.out.println(dessertLerator.next());
//		}
//		
//		Gson gS = new Gson();
//		
//		DrinkGenerator mixer = new DrinkGenerator();
//		ArrayList<Ingredient> ingres = mixer.getLists().get(ingredientType.Spirit);
//		System.out.println(ingres.toString());
		
		//RecipeBook book = new RecipeBook(datCon); 
		
//		while(templIterator.hasNext()) {
//			Document doc = templIterator.next();
//			System.out.println(doc.toJson());
//			Recipe drink = gS.fromJson(doc.toJson(), Recipe.class);
//			System.out.println(drink.toJSON());
//			Document newDoc = Document.parse(drink.toJSON());
//			System.out.println(newDoc.toString() + "\n");
//		}
		
//		RecipeBook book = new RecipeBook(datCon);
//		
//		ArrayList<Recipe> temps = book.getBook();
//		for (int i = 0; i < temps.size(); ++i) {
//			System.out.println(temps.get(i).toJSON());
//		}
	}
}
