package com.cocktailgenerator.model.Objects;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Random;

import com.cocktailgenerator.model.CocktailGenerator.ingredientType;

public class DrinkGenerator {

	private String inventoryPath;
	private EnumMap<ingredientType, ArrayList<Ingredient>> lists =  new EnumMap<>(ingredientType.class);
	
	public DrinkGenerator() {
		
		inventoryPath = "Data/MyInventory";				//The default inventory, if none is provided by a user profile		
		
		try {
			
			ArrayList<Ingredient> ingredients;
			
			for (ingredientType type : ingredientType.values()) {
				
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
	
}
