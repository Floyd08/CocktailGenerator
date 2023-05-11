package com.cocktailgenerator.exceptions;

import com.cocktailgenerator.main.ingredientType;

public class EmptyIngredientListException extends Exception {
	
	ingredientType listName;
	
	public EmptyIngredientListException() {}
	
	public EmptyIngredientListException(ingredientType listName) {
		this.listName = listName;
	}

}
