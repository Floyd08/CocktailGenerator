package CocktailGenerator;

import java.util.Random;
import Objects.Recipe;
import Objects.RecipeBook;
import Objects.DrinkGenerator;

public class TheBar {

	public static void main(String[] args) throws Exception {
		
		DrinkGenerator mixer = new DrinkGenerator();
		RecipeBook templates = new RecipeBook("Data/RecipeBook/Templates");
		
		Recipe drink = useRandomRecipe(mixer, templates);
		mixer.printRecipe(drink);

	}
	
	public static Recipe useRandomRecipe(DrinkGenerator mixer, RecipeBook book) {
		
		Random geny = new Random();
		int rando = geny.nextInt(book.getBook().size());
		
		Recipe drink = book.getBook().get(rando);
		drink = mixer.generateRecipe(drink); 
		
		return drink;
	}
}







