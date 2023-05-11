package com.cocktailgenerator.Testing;

import java.util.ArrayList;

import com.cocktailgenerator.entity.Ingredient;
import com.cocktailgenerator.entity.Recipe;
import com.google.gson.Gson;

//import Objects.Spirit;

public class Lab {

	public static void main(String[] args) {
		
		Gson gS = new Gson();
		
//		ArrayList<Ingredient> sideCar = new ArrayList<Ingredient>();
//		sideCar.add(new Ingredient("Spirit", "Spirit", "Cognac", 8));
//		sideCar.add(new Ingredient("Liqueur", "FruitLiqueur", "Curacao", 2));
//		sideCar.add(new Ingredient("Juice", "TartJuice", "Lemon", 1));
//		
//		Recipe drank = new Recipe("SideCar", sideCar);
		
		ArrayList<Ingredient> template = new ArrayList<Ingredient>();
		ArrayList<Ingredient> extras = new ArrayList<Ingredient>();
		template.add(new Ingredient("Spirit", "Spirit", "Gin", 2));
		template.add(new Ingredient("Liqueur", "Liqueur", "Sloe Gin", 2));
		template.add(new Ingredient("Juice", "TartJuice", "Lemon", 1));
		extras.add(new Ingredient("Ingredient", "Soda", "Soda", 10));
		Recipe drank = new Recipe("Sloe Gin Fizz", template, extras);
		
		String Json = gS.toJson(drank);
		
		System.out.println(Json);

	}

}
