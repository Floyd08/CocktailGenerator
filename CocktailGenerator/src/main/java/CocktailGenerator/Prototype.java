package CocktailGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import Objects.Ingredient;
import Objects.Juice;
import Objects.Liqueur;
import Objects.Spirit;
import Objects.SweetJuice;
import Objects.Syrup;
import Objects.TartJuice;

public class Prototype {

	public static void main(String[] args) {
				
		//set up lists for each type
		ArrayList<Spirit> spirits = Spirit.buildList("Data/MyInventory/Spirits");
		ArrayList<Liqueur> liqueur = Liqueur.buildList("Data/MyInventory/Liqueurs");
		ArrayList<Juice> juices = Juice.buildList("Data/MyInventory/Juices");
		ArrayList<TartJuice> tartJuices = TartJuice.buildTartJuiceList("Data/MyInventory/TartJuices");
		ArrayList<SweetJuice> sweetJuices = SweetJuice.buildSweetJuiceList("Data/MyInventory/SweetJuices");
		ArrayList<Syrup> syrups = Syrup.buildList("Data/MyInventory/Syrups");
		
		//set up hashmap to hold those lists
		HashMap<String, ArrayList<? extends Ingredient>> lists = new HashMap<String, ArrayList<? extends Ingredient> >();
		lists.put(Spirit.class.getName(), spirits);
		lists.put(Liqueur.class.getName(), liqueur);
		lists.put(Juice.class.getName(), juices);
		lists.put(TartJuice.class.getName(), tartJuices);
		lists.put(SweetJuice.class.getName(), sweetJuices);
		lists.put(Syrup.class.getName(), syrups);
		
		//A template for Sidecars
		ArrayList<Ingredient> sideCar = new ArrayList<Ingredient>();
		sideCar.add(new Spirit(8));
		sideCar.add(new Liqueur(2));
		sideCar.add(new TartJuice(1));
		
		//sideCar = generateRecipe(lists, sideCar);

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
		
		daiquiri = generateRecipe(lists, daiquiri);
		
		//Print Recipe
		//printRecipe(sideCar);
		//printRecipe(sexOnTheBeach);
		printRecipe(daiquiri);
		
		
		//generalize further by creting a generator class, and storing the HashMap there. 
		//Any call to generateRecipe would already have access to the Map

	}
	
	public static ArrayList<Ingredient> generateRecipe(HashMap<String, ArrayList<? extends Ingredient> > lists, ArrayList<Ingredient> template) {
		
		Random geny = new Random();
		//For each ingredient, get a random element from the relevant list
		for (int i = 0; i < template.size(); ++i) {
			
			String type = template.get(i).getClass().getName();
			int proportion = template.get(i).getProportion();
			
			ArrayList<? extends Ingredient> list = lists.get(type);
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
