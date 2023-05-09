package com.cocktailgenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cocktailgenerator.model.Objects.DrinkGenerator;
import com.cocktailgenerator.model.Objects.RecipeBook;

@RestController
@CrossOrigin(origins = {"${CrossOriginValue}"})
@Scope("request")
public class IngredientController {
	
	@Autowired
	private DrinkGenerator mixer;
	
	@GetMapping("/loadGuest")
	public void loadGuest() {
		mixer.loadGuestIngredients();
	}
}
