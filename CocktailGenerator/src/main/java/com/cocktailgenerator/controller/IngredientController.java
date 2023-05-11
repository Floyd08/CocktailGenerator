package com.cocktailgenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cocktailgenerator.entity.RecipeBook;
import com.cocktailgenerator.main.DrinkGenerator;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = {"${CrossOriginValue}"})
@Scope("request")
public class IngredientController {
	
	@Autowired
	private DrinkGenerator mixer;
	
	@GetMapping("/loadGuest")
	public void loadGuest() {
		mixer.loadGuestIngredients();
		System.out.println("guest loaded");
	}
}
