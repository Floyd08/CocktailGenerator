package Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class drinkGenerator {

	private String inventoryPath;
	private HashMap<String, ArrayList<Ingredient>> lists =  new HashMap<String, ArrayList<Ingredient> >();
	private ArrayList<Ingredient> spirits;
	private ArrayList<Ingredient> liqueurs;
	private ArrayList<Ingredient> juices;
	private ArrayList<Ingredient> tartJuices;
	private ArrayList<Ingredient> sweetJuices;
	private ArrayList<Ingredient> syrups;
	
	public drinkGenerator() {
		
		inventoryPath = "Data/MyInventory";				//The default inventory, if none is provided by a user profile		
		
		try {
			
			spirits = Ingredient.buildList(inventoryPath + "/Spirits");
			liqueurs = Ingredient.buildList(inventoryPath + "/Liqueurs");
			juices = Ingredient.buildList(inventoryPath + "/Juices");
			tartJuices = Ingredient.buildList(inventoryPath + "/TartJuices");
			sweetJuices = Ingredient.buildList(inventoryPath + "/SweetJuices");
			syrups = Ingredient.buildList(inventoryPath + "/Syrups");
			
			lists.put("Spirit", spirits);
			lists.put("Liqueur", liqueurs);
			lists.put("Juice", juices);
			lists.put("TartJuice", tartJuices);
			lists.put("SweetJuice", sweetJuices);
			lists.put("Syrup", syrups);
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	
	// Later, when A user loads their inventory we'll use this constructor
	public drinkGenerator(String userInventoryPath) {
		
	}
	
	public ArrayList<Ingredient> generateRecipe(ArrayList<Ingredient> template) {
		
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
	
	public void printRecipe(ArrayList<Ingredient> recipe) {
		
		String output = "";
		
		for (int i = 0; i < recipe.size(); ++i)
			output += recipe.get(i).toString() + " \n";
					
		System.out.println(output);
	}
	
}
