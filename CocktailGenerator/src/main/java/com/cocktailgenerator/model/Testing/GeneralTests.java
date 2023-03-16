package com.cocktailgenerator.model.Testing;

import java.util.ArrayList;

import com.cocktailgenerator.model.Objects.Ingredient;
import com.cocktailgenerator.model.Objects.RecipeBook;
import com.cocktailgenerator.model.Objects.DrinkGenerator;
import com.cocktailgenerator.model.Objects.RecipeFrontEnd;

public class GeneralTests {

	public static void main(String[] args) throws Exception {
		
//		drinkGenerator mixer = new drinkGenerator();
		
//		ArrayList<Ingredient> sideCar = new ArrayList<Ingredient>();
//		sideCar.add(new Ingredient("Spirit", "Spirit", "Cognac", 8));
//		sideCar.add(new Ingredient("Liqueur", "FruitLiqueur", "Curacao", 2));
//		sideCar.add(new Ingredient("Juice", "TartJuice", "Lemon", 1));
//		
//		Recipe template = new Recipe("SideCar", sideCar);
//		
//		sideCar = mixer.generateRecipe(template);
//		mixer.printRecipe(template);

		
		RecipeBook book = new RecipeBook("Data/RecipeBook/Templates");
		RecipeFrontEnd template = new RecipeFrontEnd();
		
		for (int i = 0; i < book.getBook().size(); ++i) {
			//System.out.println(i);
			//System.out.println(book.getBook().get(i).toJSON());
			
			//System.out.println(RecipeBook.describeRecipe( book.getBook().get(i)) );
			template = new RecipeFrontEnd(book.getBook().get(i), i);
			System.out.println(template.toString());
		}
	}

}
