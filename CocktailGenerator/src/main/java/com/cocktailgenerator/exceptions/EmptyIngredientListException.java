package com.cocktailgenerator.exceptions;

import com.cocktailgenerator.model.CocktailGenerator.ingredientType;

public class EmptyIngredientListException extends Exception {
	
	ingredientType listName;
	
	public EmptyIngredientListException() {}
	
	public EmptyIngredientListException(ingredientType listName) {
		this.listName = listName;
	}

}
