package Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class drinkGenerator {

	private String inventoryPath;
	private HashMap<String, ArrayList<Ingredient>> lists =  new HashMap<String, ArrayList<Ingredient> >();
	private ArrayList<Ingredient> spirits;
	private ArrayList<Ingredient> liqueurs;
	private ArrayList<Ingredient> fruitLiqueurs;
	private ArrayList<Ingredient> herbalLiqueurs;
	private ArrayList<Ingredient> aromatizedWines;
	private ArrayList<Ingredient> juices;
	private ArrayList<Ingredient> tartJuices;
	private ArrayList<Ingredient> sweetJuices;
	private ArrayList<Ingredient> syrups;
	private ArrayList<Ingredient> bitters;
	
	public drinkGenerator() {
		
		inventoryPath = "Data/MyInventory";				//The default inventory, if none is provided by a user profile		
		
		try {
			
			/*This could be done programatically!
			 *Hard code the file names(Or better yet, get them from the OS), and use these
			 *Strings to build lists and and them to the Map
			 */
			spirits = Ingredient.buildList(inventoryPath + "/Spirits");
			liqueurs = Ingredient.buildList(inventoryPath + "/Liqueurs");
			fruitLiqueurs = Ingredient.buildList(inventoryPath + "/FruitLiqueurs");
			herbalLiqueurs = Ingredient.buildList(inventoryPath + "/HerbalLiqueurs");
			aromatizedWines = Ingredient.buildList(inventoryPath + "/AromatizedWines");
			juices = Ingredient.buildList(inventoryPath + "/Juices");
			tartJuices = Ingredient.buildList(inventoryPath + "/TartJuices");
			sweetJuices = Ingredient.buildList(inventoryPath + "/SweetJuices");
			syrups = Ingredient.buildList(inventoryPath + "/Syrups");
			bitters = Ingredient.buildList(inventoryPath + "/Bitters");
			
			lists.put("Spirit", spirits);
			lists.put("Liqueur", liqueurs);
			lists.put("FruitLiqueur", fruitLiqueurs);
			lists.put("HerbalLiqueur", herbalLiqueurs);
			lists.put("AromatizedWines", aromatizedWines);
			lists.put("Juice", juices);
			lists.put("TartJuice", tartJuices);
			lists.put("SweetJuice", sweetJuices);
			lists.put("Syrup", syrups);
			lists.put("Bitters", bitters);
		} 
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	
	// Later, when A user loads their inventory we'll use this constructor
	public drinkGenerator(String userInventoryPath) {
		
	}
	
	public ArrayList<Ingredient> generateRecipe(Recipe recipe) {
		
		Random geny = new Random();
		ArrayList<Ingredient> template = recipe.getTemplate();
		//For each ingredient, get a random element from the relevant list
		for (int i = 0; i < template.size(); ++i) {
			
			String type = template.get(i).getType();
			int proportion = template.get(i).getProportion();
			//System.out.printf("Type: %s, Proportion: %s, i: %d\n", type, proportion, i);			
			
			ArrayList<Ingredient> list = lists.get(type);
			int rando = geny.nextInt(list.size() - 1);
			
			template.set(i, list.get(rando));
			template.get(i).setProportion(proportion);	
		}
		
		return template;
	}
	
	public void printRecipe(Recipe recipe) {
		
		ArrayList<Ingredient> template = recipe.getTemplate();
		String output = "";
		
		for (int i = 0; i < template.size(); ++i)
			output += template.get(i).toString() + " \n";
					
		System.out.println(output);
	}
	
}
