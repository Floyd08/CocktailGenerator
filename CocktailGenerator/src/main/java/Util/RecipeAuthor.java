package Util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import com.google.gson.Gson;

import Objects.Ingredient;
import Objects.Recipe;

public class RecipeAuthor {

	public static void main(String[] args) {
		
		ArrayList<Recipe> recipeDump = new ArrayList<Recipe>();
		
		ArrayList<Ingredient> template = new ArrayList<Ingredient>();
		template.add(new Ingredient("Spirit", "Spirit", "Cognac", 8));
		template.add(new Ingredient("Liqueur", "FruitLiqueur", "Curacao", 2));
		template.add(new Ingredient("Juice", "TartJuice", "Lemon", 1));
		Recipe drank = new Recipe("Sidecar", template);
		recipeDump.add(drank);
		
		template = new ArrayList<Ingredient>();
		template.add(new Ingredient("Spirit", "Spirit", "Vodka", 2));
		template.add(new Ingredient("Juice", "SweetJuice", "Orange", 2));
		template.add(new Ingredient("Juice", "TartJuice", "Cranberry", 2));
		template.add(new Ingredient("Liqueur", "FruitLiqueur", "Peach Schnapps", 1));
		drank = new Recipe("Sex on the beach", template);
		recipeDump.add(drank);
		
		template = new ArrayList<Ingredient>();
		template.add(new Ingredient("Spirit", "Spirit", "Light Rum", 8));
		template.add(new Ingredient("Juice", "TartJuice", "Lime", 2));
		template.add(new Ingredient("Syrup", "Syrup", "Simple Syrup", 1));
		drank = new Recipe("Daiquiri", template);
		recipeDump.add(drank);
		
		template = new ArrayList<Ingredient>();
		template.add(new Ingredient("Spirit", "Spirit", "Gin", 1));
		template.add(new Ingredient("Liqueur", "HerbalLiqueur", "Green Chartreuse", 1));
		template.add(new Ingredient("Liqueur", "FruitLiqueur", "Maraschino", 1));
		template.add(new Ingredient("Juice", "TartJuice", "Lime", 1));
		drank = new Recipe("Last Word", template);
		recipeDump.add(drank);
		
		template = new ArrayList<Ingredient>();
		template.add(new Ingredient("Spirit", "Spirit", "Whiskey", 6));
		template.add(new Ingredient("Syrup", "Syrup", "Simple Syrup", 1));
		template.add(new Ingredient("Bitters", "Bitters", "Angostura", 0));
		drank = new Recipe("Old Fashioned", template);
		recipeDump.add(drank);
		
		template = new ArrayList<Ingredient>();
		template.add(new Ingredient("Spirit", "Spirit", "Whiskey", 2));
		template.add(new Ingredient("AromatizedWine", "AromatizedWine", "Sweet Vermouth", 1));
		template.add(new Ingredient("Bitters", "Bitters", "Angostura", 0));
		drank = new Recipe("Manhattan", template);
		recipeDump.add(drank);
		
		try {
		
			Gson gS = new Gson();
			BufferedWriter writer = new BufferedWriter(new FileWriter("Data/RecipeBook/Templates"));
			
			for (int i = 0; i < recipeDump.size(); ++i) {
				//System.out.println(gS.toJson(recipeDump.get(i)));
				writer.write( gS.toJson(recipeDump.get(i)) );
				writer.newLine();
			}
			writer.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
