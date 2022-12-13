package CocktailGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import Objects.Ingredient;
import Objects.Juice;
import Objects.Liqueur;
import Objects.Spirit;

public class Prototype {

	public static void main(String[] args) {
		
		Random geny = new Random();
		
		//set up lists for each type
		ArrayList<Spirit> spirits = Spirit.buildList();
		ArrayList<Liqueur> liqueur = Liqueur.buildList();
		ArrayList<Juice> juices = Juice.buildList();
		
		//set up hashmap to hold those lists
		HashMap<String, ArrayList<? extends Ingredient>> lists = new HashMap<String, ArrayList<? extends Ingredient> >();
		lists.put(Spirit.class.getName(), spirits);
		lists.put(Liqueur.class.getName(), liqueur);
		lists.put(Juice.class.getName(), juices);
		
		//A template for Sidecars
		ArrayList<Ingredient> sideCar = new ArrayList<Ingredient>();
		sideCar.add(new Spirit(8));
		sideCar.add(new Liqueur(2));
		sideCar.add(new Juice(1));
		
		sideCar = generateRecipe(lists, sideCar);
		
		//generalize further by creting a generator class, and storing the HashMap there. 
		//Any call to generateRecipe would already have access to the Map
		
		//Print Recipe
		printRecipe(sideCar);

	}
	
	public static ArrayList<Ingredient> generateRecipe(HashMap<String, ArrayList<? extends Ingredient> > lists, ArrayList<Ingredient> template) {
		
		Random geny = new Random();
		//For each ingredient, get a random element from the relevant list
		for (int i = 0; i < template.size(); ++i) {
			
			String type = template.get(i).getClass().getName();
			int proportion = template.get(i).getProportion();
			
			ArrayList<? extends Ingredient> list = lists.get(type);
			int rando = geny.nextInt(list.size() - 1);
			
			template.set(i, list.get(rando));
			template.get(i).setProportion(proportion);	
		}
		
		return template;
	}
	
	public static void printRecipe(ArrayList<Ingredient> recipe) {
		
		String output = "";
		
		for (int i = 0; i < recipe.size(); ++i)
			output += recipe.get(i).getProportion() + " parts " + recipe.get(i).getLabel() + " \n";
		
		System.out.println(output);
	}

}
