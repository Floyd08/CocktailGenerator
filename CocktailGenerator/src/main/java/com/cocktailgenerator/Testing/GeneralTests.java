package com.cocktailgenerator.Testing;

import java.util.ArrayList;

import com.cocktailgenerator.entity.Ingredient;
import com.cocktailgenerator.entity.RecipeBook;
import com.cocktailgenerator.entity.RecipeFrontEnd;
import com.cocktailgenerator.main.DrinkGenerator;

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
		
		RecipeFrontEnd newDrink = new RecipeFrontEnd(book.getBook().get(0));
		System.out.println(newDrink);
		
//		RecipeFrontEnd template = new RecipeFrontEnd();
//		
//		for (int i = 0; i < book.getBook().size(); ++i) {
//			//System.out.println(i);
//			//System.out.println(book.getBook().get(i).toJSON());
//			
//			//System.out.println(RecipeBook.describeRecipe( book.getBook().get(i)) );
//			template = new RecipeFrontEnd(book.getBook().get(i), i);
//			System.out.println(template.toString());
//		}
	}

}
