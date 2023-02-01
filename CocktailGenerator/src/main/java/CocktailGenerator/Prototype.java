package CocktailGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import Objects.Ingredient;

public class Prototype {

	public static void main(String[] args) throws Exception {
				
		//set up lists for each type
		ArrayList<Ingredient> spirits = Ingredient.buildList("Data/MyInventory/Spirits");
		ArrayList<Ingredient> liqueurs = Ingredient.buildList("Data/MyInventory/Liqueurs");
		ArrayList<Ingredient> juices = Ingredient.buildList("Data/MyInventory/Juices");
		ArrayList<Ingredient> tartJuices = Ingredient.buildList("Data/MyInventory/TartJuices");
		ArrayList<Ingredient> sweetJuices = Ingredient.buildList("Data/MyInventory/SweetJuices");
		ArrayList<Ingredient> syrups = Ingredient.buildList("Data/MyInventory/Syrups");
		
		//set up hashmap to hold those lists
		HashMap<String, ArrayList<Ingredient>> lists = new HashMap<String, ArrayList<Ingredient> >();
		lists.put("Spirit", spirits);
		lists.put("Liqueur", liqueurs);
		lists.put("Juice", juices);
		lists.put("TartJuice", tartJuices);
		lists.put("SweetJuice", sweetJuices);
		lists.put("Syrup", syrups);
		
		//A template for Sidecars
		/*ArrayList<Ingredient> sideCar = new ArrayList<Ingredient>();
		sideCar.add(new Spirit(8));
		sideCar.add(new Liqueur(2));
		sideCar.add(new TartJuice(1));*/
		
		ArrayList<Ingredient> sideCar = new ArrayList<Ingredient>();
		sideCar.add(new Ingredient("Spirit", "Spirit", "Cognac", 8));
		sideCar.add(new Ingredient("Liqueur", "Liqueur", "Curacao", 2));
		sideCar.add(new Ingredient("Juice", "TartJuice", "Lemon", 1));
		
		sideCar = generateRecipe(lists, sideCar);
		/*
		ArrayList<Ingredient> sexOnTheBeach = new ArrayList<Ingredient>();
		sexOnTheBeach.add(new Spirit(2));
		sexOnTheBeach.add(new SweetJuice(2));
		sexOnTheBeach.add(new TartJuice(2));
		sexOnTheBeach.add(new Liqueur(1));
		
		//sexOnTheBeach = generateRecipe(lists, sexOnTheBeach);
		
		ArrayList<Ingredient> daiquiri = new ArrayList<Ingredient>();
		daiquiri.add(new Spirit(8));
		daiquiri.add(new TartJuice(2));
		daiquiri.add(new Syrup(1));
		*/
		//daiquiri = generateRecipe(lists, sideCar);
		
		//Print Recipe
		printRecipe(sideCar);
		//printRecipe(sexOnTheBeach);
		//printRecipe(daiquiri);

	}
	
	public static ArrayList<Ingredient> generateRecipe(HashMap<String, ArrayList<Ingredient> > lists, ArrayList<Ingredient> template) {
		
		Random geny = new Random();
		//For each ingredient, get a random element from the relevant list
		for (int i = 0; i < template.size(); ++i) {
			
			String type = template.get(i).getType();
			int proportion = template.get(i).getProportion();
			
			ArrayList<Ingredient> list = lists.get(type);
			int rando = geny.nextInt(list.size());
			
			template.set(i, list.get(rando));
			template.get(i).setProportion(proportion);	
		}
		
		return template;
	}
	
	public static void printRecipe(ArrayList<Ingredient> recipe) {
		
		String output = "";
		
		for (int i = 0; i < recipe.size(); ++i)
			output += recipe.get(i).toString() + " \n";
			
		//output += recipe.get(i).getProportion() + " parts " + recipe.get(i).getLabel() + " \n";
		
		System.out.println(output);
	}

}
