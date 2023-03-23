package com.cocktailgenerator.model.Objects;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Random;

import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.cocktailgenerator.main.DataConnection;
import com.cocktailgenerator.model.CocktailGenerator.ingredientType;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Projections;

public class DrinkGenerator {

	private String inventoryPath;
	private EnumMap<ingredientType, ArrayList<Ingredient>> lists =  new EnumMap<>(ingredientType.class);
	
	public DrinkGenerator(DataConnection datCon) {
		
		ArrayList<Ingredient> ingredients;
		MongoCollection<Document> ingredientsCOL = datCon.getDB().getCollection("Ingredients");
		FindIterable<Document> subSetIngredients;
		Bson projectionFields = Projections.fields(
				Projections.include("superType", "type", "subType", "proportion"), 
				Projections.excludeId());
		
		try {
			for (ingredientType type : ingredientType.values()) {
				
				if (type == ingredientType.Spirit || type == ingredientType.Juice || type == ingredientType.Liqueur) {
					subSetIngredients = ingredientsCOL.find(eq("superType", type.toString()))
							.projection(projectionFields);
				}
				else {
					subSetIngredients = ingredientsCOL.find(eq("type", type.toString()))
							.projection(projectionFields);
				}
				Iterator<Document> subSetErator = subSetIngredients.iterator();
				ingredients = Ingredient.buildList(subSetErator);
				lists.put(type, ingredients);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public DrinkGenerator() {
		
		inventoryPath = "Data/MyInventory";				//The default inventory, if none is provided by a user profile		
		
		try {
			
			ArrayList<Ingredient> ingredients;
			
			for (ingredientType type : ingredientType.values()) {
				
				//get a subset of the collection, then pass it to buildList to turn it into an arrayList. Then execution continues as is.
				ingredients = Ingredient.buildList(inventoryPath + "/" + type.toString());
				//lists.put(type.toString(), ingredients);
				lists.put(type, ingredients);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	
	// Later, when A user loads their inventory we'll use this constructor
	public DrinkGenerator(String userInventoryPath) {
		
	}
	
	public Recipe generateRecipe(Recipe recipe) {
		
		Random geny = new Random();
		ArrayList<Ingredient> template = recipe.getTemplate();
		//For each ingredient, get a random element from the relevant list
		for (int i = 0; i < template.size(); ++i) {
			
			String type = template.get(i).getType();
			int proportion = template.get(i).getProportion();
			//System.out.printf("Type: %s, Proportion: %s, i: %d\n", type, proportion, i);			
			
			ArrayList<Ingredient> list = lists.get(ingredientType.valueOf(type));
			int rando = geny.nextInt(list.size() - 1);
			
			template.set(i, list.get(rando));
			template.get(i).setProportion(proportion);	
		}
		recipe.setTemplate(template);
		
		return recipe;
	}
	
	public Recipe trueRandom(int numIngredients) {
		
		Random geny = new Random();
		ArrayList<Ingredient> template = new ArrayList<Ingredient>();
		int types = ingredientType.values().length;
		int rando;
		
		for (int i = 0; i < numIngredients; ++i) {
			
			rando = geny.nextInt(types);
			ArrayList<Ingredient> list = lists.get(ingredientType.getType(rando));
			
			rando = geny.nextInt(list.size() - 1);
			template.add(i, list.get(rando));
			rando = geny.nextInt(5) + 1;
			template.get(i).setProportion(rando);
		}
		
		return new Recipe("TrueRandom", template);
	}
	
	public String printRecipe(Recipe recipe) {
		
		ArrayList<Ingredient> template = recipe.getTemplate();
		String output = "Template used: " + recipe.getName() + " \n";
		
		for (int i = 0; i < template.size(); ++i) {
			
			if ( template.get(i).getType().equals("Bitters") ) {
				output += template.get(i).proportion + " dashes " + template.get(i).subType + " bitters \n";
			}
			else {
				output += template.get(i).toString() + " \n";
			}
		}
		
		if ( recipe.getExtras() != null ) {
			
			ArrayList<Ingredient> extras = recipe.getExtras();
			
			for (int i = 0; i < extras.size(); ++i) {
				output += extras.get(i).toString() + " \n";
			}
		}
			
		//System.out.println("\n" + output);
		return output;
	}

	public EnumMap<ingredientType, ArrayList<Ingredient>> getLists() {
		return lists;
	}
	
}
