package com.cocktailgenerator.model.CocktailGenerator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import com.cocktailgenerator.model.Objects.Recipe;
import com.cocktailgenerator.model.Objects.RecipeBook;
import com.cocktailgenerator.model.Objects.DrinkGenerator;

public class TheBar {

	public static void main(String[] args) throws Exception {
		
		DrinkGenerator mixer = new DrinkGenerator();
		RecipeBook templates = new RecipeBook("Data/RecipeBook/Templates");
		
		//Recipe drink = useRandomRecipe(mixer, templates);
		//mixer.printRecipe(drink);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please choose a template(pick a number). Enter anything else to quite");
		printMenu(templates);
		
		int input = getInput(reader);
		
		int numChoses = templates.getBook().size();
		while (input > -1 && input <= numChoses + 1) {
			
			Recipe drink;
			System.out.println(numChoses);
			
			if (input == numChoses) {
				drink = useRandomRecipe(mixer, templates);
			}
			else if (input == numChoses + 1) {
				drink = mixer.trueRandom(4);
			}
			else {
				drink = useRecipe(mixer, templates, input);
			}
			mixer.printRecipe(drink);
			
			System.out.println("Try another! Or enter anything else to quite");
			printMenu(templates);
			
			input = getInput(reader);
		}
		reader.close();
	}
	
	public static Recipe useRandomRecipe(DrinkGenerator mixer, RecipeBook book) {
		
		Random geny = new Random();
		int rando = geny.nextInt(book.getBook().size());
		
		Recipe drink = new Recipe(book.getBook().get(rando));
		drink = mixer.generateRecipe(drink); 
		
		return drink;
	}
	
	public static Recipe useRecipe(DrinkGenerator mixer, RecipeBook book, int n) {
		
		Recipe drink = new Recipe(book.getBook().get(n));
		drink = mixer.generateRecipe(drink); 
		
		return drink;
	}
	
	public static void printMenu(RecipeBook templates) {
		
		int i = 0;
		for ( Recipe template : templates.getBook() ) {
			
			System.out.printf("(%d) %s\n", i, template.getName());
			++i;
		}
		System.out.printf("(%d) Surprise me (Choose random template)\n", i);
		++i;
		System.out.printf("(%d) I'm feeling lucky (Totally Random)\n", i);
		
	}
	
	public static int getInput(BufferedReader reader) throws Exception{
		
		//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String rawInput = reader.readLine();
		int input;
		
		try {
			
			input = Integer.valueOf(rawInput);
		}
		catch (NumberFormatException e) {
			
			input = -1;
		}
		//reader.close();
		
		return input;
	}
}







