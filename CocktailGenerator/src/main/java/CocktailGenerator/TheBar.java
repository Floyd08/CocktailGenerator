package CocktailGenerator;

import java.util.ArrayList;

import Objects.Ingredient;
import Objects.Recipe;
import Objects.drinkGenerator;

public class TheBar {

	public static void main(String[] args) {
		
		drinkGenerator mixer = new drinkGenerator();
		
		ArrayList<Ingredient> sideCar = new ArrayList<Ingredient>();
		sideCar.add(new Ingredient("Spirit", "Spirit", "Cognac", 8));
		sideCar.add(new Ingredient("Liqueur", "FruitLiqueur", "Curacao", 2));
		sideCar.add(new Ingredient("Juice", "TartJuice", "Lemon", 1));
		
		Recipe template = new Recipe("SideCar", sideCar);
		
		sideCar = mixer.generateRecipe(template);
		mixer.printRecipe(template);

	}
}
