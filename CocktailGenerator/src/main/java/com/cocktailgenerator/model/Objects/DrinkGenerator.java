package com.cocktailgenerator.model.Objects;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Random;

import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.cocktailgenerator.exceptions.EmptyIngredientListException;
import com.cocktailgenerator.main.DataConnection;
import com.cocktailgenerator.model.CocktailGenerator.ingredientType;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

public class DrinkGenerator {

	private String inventoryPath;
	private EnumMap<ingredientType, ArrayList<Ingredient>> lists =  new EnumMap<>(ingredientType.class);
	
	//The 'User' constructor. After validation, loads ingredients from the user's own list of ingredients
	public DrinkGenerator(DataConnection datCon) {
		
		String typeToggle = "type";
		ArrayList<Ingredient> ingredients;
		MongoCollection<Document> ingredientsCOL = datCon.getDB().getCollection("Ingredients");
		FindIterable<Document> subSetIngredients;
		Bson projectionFields = Projections.fields(
				Projections.include("superType", "type", "subType", "proportion"), 
				Projections.excludeId());
		
		try {
			for (ingredientType type : ingredientType.values()) {
				
				if (type == ingredientType.Spirit || type == ingredientType.Juice || type == ingredientType.Liqueur) {
					typeToggle = "superType";							//More General lists
				}
				else {
					typeToggle = "type";								//Specific lists
				}
				Bson filter = Filters.eq(typeToggle, type.toString());
				subSetIngredients = ingredientsCOL.find(filter)				
						.projection(projectionFields);
				
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
	
	//The 'Guest' constructor. Loads ingredients from the master list of all ingredients
	public DrinkGenerator(DataConnection datCon, String userName) {
		
		String typeToggle = "type";
		ArrayList<Ingredient> ingredients;
		MongoCollection<Document> ingredientsCOL = datCon.getDB().getCollection("UserIngredients");
		FindIterable<Document> userIngredients, subSetIngredients;
		Bson projectionFields = Projections.fields(
				Projections.include("superType", "type", "subType", "proportion"), 
				Projections.excludeId());
		
		try {
			for (ingredientType type : ingredientType.values()) {
				
				
				if (type == ingredientType.Spirit || type == ingredientType.Juice || type == ingredientType.Liqueur) {
					typeToggle = "superType";
				}
				else {
					typeToggle = "type";
				}
				
				Bson filter = Filters.and(Filters.eq("owner", userName), Filters.eq(typeToggle, type.toString()));
				subSetIngredients = ingredientsCOL.find(filter)
						.projection(projectionFields);
				
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
	
	
	/**
	 * 	The meat and potatoes of the whole app. Takes any recipe, presumably a template recipe, and randomizes the ingredients based on type or SuperType
	 *  @param 		recipe A recipe object, whose ingredients will be randomized by this method
	 *  @return 	The randomized recipe
	 */
	public Recipe generateRecipe(Recipe recipe) {
		
		Random geny = new Random();
		ArrayList<Ingredient> template = recipe.getTemplate();
		//For each ingredient, get a random element from the relevant list
		for (int i = 0; i < template.size(); ++i) {
			
			String type = template.get(i).getType();
			int proportion = template.get(i).getProportion();
			//System.out.printf("Type: %s, Proportion: %s, i: %d\n", type, proportion, i);			
			
			ArrayList<Ingredient> list = lists.get(ingredientType.valueOf(type));
			
			if (list.isEmpty()) {
				//Do nothing, and leave this ingredient as it is
			}
			else {
				int rando = geny.nextInt(list.size() - 1);
				
				template.set(i, list.get(rando));
				template.get(i).setProportion(proportion);
			}
		}
		recipe.setTemplate(template);
		
		return recipe;
	}
	
	/**
	 * Generates a completely random recipe, using no template and limited only by the number of ingredients specified
	 */
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
	
	/*
	 * A jumped up toString method. Reads the recipe object and packages it into a String suitable for the User
	 */
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
	
	/**
	 * Returns the EnumMap holding all ingredients available to the generator
	 */
	public EnumMap<ingredientType, ArrayList<Ingredient>> getLists() {
		return lists;
	}
	
	/**
	 * Dumps the contents of the drink generator's EnumMap into a single list
	 */
	public ArrayList<Ingredient> flattenEnumMap() {
		
		ArrayList<Ingredient> masterList = new ArrayList<Ingredient>();
		
		for (int i = 0; i < ingredientType.values().length; ++i) {
			masterList.addAll( this.getLists().get(ingredientType.getType(i)) );		//uses the mapping of Types to integer values in ingredientType to dump the EnumMap
		}
		
		return masterList;
	}
	
}
